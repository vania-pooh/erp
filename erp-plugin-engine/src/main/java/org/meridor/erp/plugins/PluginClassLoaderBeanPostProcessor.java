package org.meridor.erp.plugins;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class PluginClassLoaderBeanPostProcessor implements BeanPostProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(PluginClassLoaderBeanPostProcessor.class);

    private final UberClassLoader uberClassLoader;

    public PluginClassLoaderBeanPostProcessor(UberClassLoader uberClassLoader) {
        this.uberClassLoader = uberClassLoader;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof UberClassLoader) {
            LOG.debug("Returning bean class loader as UberClassLoader instance");
            return uberClassLoader;
        }
        return bean;
    }
}
