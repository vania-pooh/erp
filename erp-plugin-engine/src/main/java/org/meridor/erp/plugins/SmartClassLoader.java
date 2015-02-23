package org.meridor.erp.plugins;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SmartClassLoader extends ClassLoader {

    private Map<String, ClassLoader> classLoaders = new HashMap<>();
    private Map<Path, ClassLoader> pathClassLoaders = new HashMap<>();

    public void addClass(Class someClass) {
        classLoaders.put(someClass.getCanonicalName(), someClass.getClassLoader());
    }
    
    public void addPathClassLoader(Path path, ClassLoader classLoader) {
        pathClassLoaders.put(path, classLoader);
    }
    
    public Optional<ClassLoader> getPathClassLoader(Path path) {
        return Optional.ofNullable(pathClassLoaders.get(path));
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (classLoaders.containsKey(name)) {
            return classLoaders.get(name).loadClass(name);
        }
        return super.loadClass(name, resolve);
    }
}
