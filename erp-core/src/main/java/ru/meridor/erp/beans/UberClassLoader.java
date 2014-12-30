package ru.meridor.erp.beans;

import java.util.HashMap;
import java.util.Map;

public class UberClassLoader extends ClassLoader {

    private Map<String, ClassLoader> classLoaders = new HashMap<>();

    public void addMapping(String name, ClassLoader classLoader) {
        classLoaders.put(name, classLoader);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (classLoaders.containsKey(name)) {
            return classLoaders.get(name).loadClass(name);
        }
        return super.loadClass(name, resolve);
    }
}
