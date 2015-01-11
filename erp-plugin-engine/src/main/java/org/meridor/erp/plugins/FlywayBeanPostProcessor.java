package org.meridor.erp.plugins;

import org.flywaydb.core.Flyway;
import org.meridor.erp.io.ResourceCategory;
import org.meridor.stecker.PluginRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.List;
import java.util.stream.Collectors;

import static org.meridor.erp.io.StreamUtils.commaSeparated;

public class FlywayBeanPostProcessor implements BeanPostProcessor {
    
    private static final Logger LOG = LoggerFactory.getLogger(FlywayBeanPostProcessor.class);
    
    private final PluginRegistry pluginRegistry;

    public FlywayBeanPostProcessor(PluginRegistry pluginRegistry) {
        this.pluginRegistry = pluginRegistry;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Flyway) {
            Flyway flyway = (Flyway) bean;
            String[] flywayLocations = getFlywayLocations();
            if (flywayLocations.length > 0) {
                LOG.debug(String.format("Setting Flyway locations to %s", commaSeparated(flywayLocations)));
                flyway.setLocations(flywayLocations);
            }
        }
        return bean;
    }
    
    private String[] getFlywayLocations() {
        List<String> migrationLocations = pluginRegistry.getResources().stream()
                .filter(path -> ResourceCategory.SQL_MIGRATION.getPathMatcher().matches(path))
                .map(path -> path.getParent().toString())
                .distinct()
                .map(s -> "filesystem:" + s)
                .collect(Collectors.toList());
        return migrationLocations.toArray(new String[migrationLocations.size()]);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
