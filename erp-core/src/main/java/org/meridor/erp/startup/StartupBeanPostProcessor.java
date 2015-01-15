package org.meridor.erp.startup;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.HashMap;
import java.util.Map;

public class StartupBeanPostProcessor implements BeanPostProcessor {

    private int addedTasksCount = 0;

    private Map<String, BeanStatus> beanStatusMap = new HashMap<String, BeanStatus>() {
        {
            put("dataSource", new BeanStatus("Connecting to database...", 0.3));
            put("flyway", new BeanStatus("Applying database migrations...", 0.5));
            put("entityManagerFactory", new BeanStatus("Processing database entities...", 0.7));
            put("mainContainer", new BeanStatus("Creating user interface...", 0.9));
        }
    };

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanStatusMap.containsKey(beanName)) {
            BeanStatus beanStatus = beanStatusMap.get(beanName);
            StartupStatus.addTask(beanStatus);
            addedTasksCount++;
            if (addedTasksCount == beanStatusMap.size()) {
                StartupStatus.setHasMoreTasks(false);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
