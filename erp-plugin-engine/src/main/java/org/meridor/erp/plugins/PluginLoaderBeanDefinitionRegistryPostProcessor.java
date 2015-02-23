package org.meridor.erp.plugins;

import org.meridor.erp.annotation.Controller;
import org.meridor.erp.io.ResourceCategory;
import org.meridor.stecker.PluginException;
import org.meridor.stecker.PluginLoader;
import org.meridor.stecker.PluginMetadata;
import org.meridor.stecker.PluginRegistry;
import org.meridor.stecker.interfaces.Dependency;
import org.meridor.stecker.interfaces.DependencyProblem;
import org.meridor.steve.Job;
import org.meridor.steve.annotations.JobCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;

import javax.persistence.Entity;
import java.beans.Introspector;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.meridor.erp.io.StreamUtils.commaSeparated;
import static org.springframework.beans.factory.support.AbstractBeanDefinition.AUTOWIRE_BY_TYPE;

public class PluginLoaderBeanDefinitionRegistryPostProcessor extends ConfigurationClassPostProcessor implements BeanDefinitionRegistryPostProcessor, ApplicationEventPublisherAware, ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(PluginLoaderBeanDefinitionRegistryPostProcessor.class);

    private PluginRegistry pluginRegistry;

    private ApplicationEventPublisher eventPublisher;

    private SmartClassLoader beanClassLoader = new SmartClassLoader();

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        pluginRegistry = loadPlugins();
        List<Class> extensionPoints = pluginRegistry.getExtensionPoints();

        for (Class extensionPoint : extensionPoints) {
            List<Class> implementations = pluginRegistry.getImplementations(extensionPoint);
            LOG.debug(String.format(
                    "Found %d implementations for %s extension point: [%s]",
                    implementations.size(),
                    extensionPoint.getCanonicalName(),
                    commaSeparated(implementations.stream().map(Class::getCanonicalName))
            ));
            for (Class implementation : implementations) {
                processImplementation(registry, implementation);
            }
        }

        List<String> pluginNames = pluginRegistry.getPluginNames();
        for (String pluginName : pluginNames) {
            List<Path> pluginResources = pluginRegistry.getResources(pluginName);
            LOG.debug(String.format(
                    "Found %d resource files for plugin %s: [%s]",
                    pluginResources.size(),
                    pluginName,
                    commaSeparated(pluginResources.stream().map(Path::toString))
            ));
            
            Optional<ClassLoader> pluginClassLoader = pluginRegistry.getClassLoader(pluginName);
            if (pluginClassLoader.isPresent()) {
                for (Path pluginResource : pluginResources) {
                    beanClassLoader.addPathClassLoader(pluginResource, pluginClassLoader.get());
                }
            }
        }
        
        setBeanClassLoader(beanClassLoader);
        super.postProcessBeanDefinitionRegistry(registry);
    }

    private void processImplementation(BeanDefinitionRegistry registry, Class implementation) {
        if (!implementation.isInterface() && !Modifier.isAbstract(implementation.getModifiers())) {
            processRegularClass(registry, implementation);
            addToBeanClassLoader(implementation);
        } else if (implementation.isInterface() && Repository.class.isAssignableFrom(implementation)) {
            processRepositoryInterface(registry, implementation);
            addToBeanClassLoader(implementation);
        } else {
            LOG.warn(String.format("Skipping unknown extension point %s", implementation.getCanonicalName()));
        }
    }

    private void processRegularClass(BeanDefinitionRegistry registry, Class implementation) {
        LOG.debug(String.format("Registering bean definition for implementation class %s", implementation.getCanonicalName()));
        String beanName = implementation.getCanonicalName();
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(implementation);
        beanDefinition.setAutowireMode(AUTOWIRE_BY_TYPE);
        registry.registerBeanDefinition(beanName, beanDefinition);
    }

    private void processRepositoryInterface(BeanDefinitionRegistry registry, Class repositoryInterface) {
        LOG.debug(String.format("Registering bean definition for repository interface %s", repositoryInterface.getCanonicalName()));
        String beanName = Introspector.decapitalize(repositoryInterface.getSimpleName());
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(JpaRepositoryFactoryBean.class);
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.add("repositoryInterface", repositoryInterface);
        beanDefinition.setPropertyValues(mutablePropertyValues);
        registry.registerBeanDefinition(beanName, beanDefinition);
    }

    private void addToBeanClassLoader(Class implementation) {
        LOG.debug(String.format("Adding implementation %s to SmartClassLoader", implementation.getCanonicalName()));
        beanClassLoader.addClass(implementation);
    }

    private PluginRegistry loadPlugins() {
        Path pluginsDirectory = getPluginsDirectory();
        Class[] extensionPoints = getExtensionPoints();
        String[] resourcesPatterns = getResourcesPatterns();
        LOG.info(String.format(
                "Loading plugins from %s with extension points = [%s] and resource patterns = [%s]",
                pluginsDirectory,
                commaSeparated(Arrays.stream(extensionPoints).map(Class::getCanonicalName)),
                commaSeparated(resourcesPatterns)
        ));

        try {
            return loadPlugins(pluginsDirectory, extensionPoints, resourcesPatterns);
        } catch (PluginException e) {
            handlePluginException(e);
            throw new BeanDefinitionValidationException("Failed to load plugins", e);
        }
    }

    protected PluginRegistry loadPlugins(Path pluginsDirectory, Class[] extensionPoints, String[] resourcesPatterns) throws PluginException {
        return PluginLoader
                .withPluginDirectory(pluginsDirectory)
                .withExtensionPoints(extensionPoints)
                .withResourcesPatterns(resourcesPatterns)
                .load();
    }

    private void handlePluginException(PluginException e) {
        Optional<PluginMetadata> possiblePluginMetadata = e.getPluginMetadata();
        if (possiblePluginMetadata.isPresent()) {
            PluginMetadata pluginMetadata = possiblePluginMetadata.get();
            String pluginName = pluginMetadata.getName();
            String pluginVersion = pluginMetadata.getVersion();
            LOG.error(String.format("Failed to load plugin %s-%s", pluginName, pluginVersion));
        }

        Optional<DependencyProblem> possibleDependencyProblem = e.getDependencyProblem();
        if (possibleDependencyProblem.isPresent()) {
            DependencyProblem dependencyProblem = possibleDependencyProblem.get();
            LOG.error("Dependency problem detected");

            if (!dependencyProblem.getMissingDependencies().isEmpty()) {
                String missingDependencies = commaSeparated(
                        dependencyProblem.getMissingDependencies().stream().map(Dependency::toString)
                );
                LOG.error(String.format("The following dependencies are missing: [%s]", missingDependencies));
            }

            if (!dependencyProblem.getConflictingDependencies().isEmpty()) {
                String conflictingDependencies = commaSeparated(
                        dependencyProblem.getConflictingDependencies().stream().map(Dependency::toString)
                );
                LOG.error(String.format("This plugin conflicts with the following dependencies: [%s]", conflictingDependencies));
            }
        }
    }

    protected Path getPluginsDirectory() {
        return Paths.get(System.getProperty("user.dir"), "plugins");
    }

    private Class[] getExtensionPoints() {
        return new Class[]{
                //Jobs
                Job.class,
                org.meridor.steve.annotations.Job.class,
                JobCollection.class,

                //UI
                Controller.class,

                //Persistence
                Entity.class,
                Repository.class,

                //Spring configuration classes
                Configuration.class
        };
    }

    private String[] getResourcesPatterns() {
        return new String[]{
                ResourceCategory.FXML_FILE.getGlob(),
                ResourceCategory.SQL_MIGRATION.getGlob()
        };
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.setBeanClassLoader(beanClassLoader);
        beanFactory.addBeanPostProcessor(new PluginClassLoaderBeanPostProcessor(beanClassLoader));
        beanFactory.addBeanPostProcessor(new FlywayBeanPostProcessor(pluginRegistry));
        super.postProcessBeanFactory(beanFactory);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        eventPublisher.publishEvent(new PluginsLoadedEvent(this, pluginRegistry));
    }

}
