package org.meridor.erp.plugins;

import java.util.HashMap;
import java.util.Map;

public class UberClassLoader extends ClassLoader {

    private Map<String, ClassLoader> classLoaders = new HashMap<>();

    public void addClass(Class someClass) {
        classLoaders.put(someClass.getCanonicalName(), someClass.getClassLoader());
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (classLoaders.containsKey(name)) {
            return classLoaders.get(name).loadClass(name);
        }
        return super.loadClass(name, resolve);
    }
}
