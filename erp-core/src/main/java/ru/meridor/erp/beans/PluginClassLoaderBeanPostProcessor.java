package ru.meridor.erp.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.List;

public class PluginClassLoaderBeanPostProcessor implements BeanPostProcessor {

    private final List<Class> implementations;

    public PluginClassLoaderBeanPostProcessor(List<Class> implementations) {
        this.implementations = implementations;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof UberClassLoader) {
            for (Class implementation : implementations) {
                ((UberClassLoader) bean).addMapping(implementation.getCanonicalName(), implementation.getClassLoader());
            }
        }
        return bean;
    }
}
