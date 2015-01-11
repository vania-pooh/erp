package org.meridor.erp.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;
import org.springframework.util.ClassUtils;

import javax.persistence.Entity;

public class PluginPersistenceUnitPostProcessor implements PersistenceUnitPostProcessor, ApplicationContextAware {

    private static final Logger LOG = LoggerFactory.getLogger(PluginPersistenceUnitPostProcessor.class);

    private ApplicationContext applicationContext;

    @Override
    public void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo pui) {
        String[] beanNames = applicationContext.getBeanNamesForAnnotation(Entity.class);
        for (String beanName : beanNames) {
            Object bean = applicationContext.getBean(beanName);
            String beanClassName = bean.getClass().getCanonicalName();
            LOG.debug(String.format("Adding persistence unit for %s", beanClassName));
            pui.addManagedClassName(beanClassName);

            //This allows Hibernate to correctly load dynamic entities
            ClassUtils.overrideThreadContextClassLoader(bean.getClass().getClassLoader());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
