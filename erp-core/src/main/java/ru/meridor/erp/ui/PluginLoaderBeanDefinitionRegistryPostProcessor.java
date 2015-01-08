package ru.meridor.erp.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import ru.meridor.erp.annotation.Controller;
import ru.meridor.stecker.PluginException;
import ru.meridor.stecker.PluginLoader;
import ru.meridor.stecker.PluginMetadata;
import ru.meridor.stecker.PluginRegistry;
import ru.meridor.stecker.interfaces.Dependency;
import ru.meridor.stecker.interfaces.DependencyProblem;
import ru.meridor.steve.Job;
import ru.meridor.steve.annotations.JobCollection;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PluginLoaderBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, ApplicationEventPublisherAware, ApplicationListener<ContextRefreshedEvent> {

    public static final String LIST_SEPARATOR = ", ";

    private static final Logger LOG = LoggerFactory.getLogger(PluginLoaderBeanDefinitionRegistryPostProcessor.class);

    private PluginRegistry pluginRegistry;

    private ApplicationEventPublisher eventPublisher;
    
    private List<Class> implementations = new ArrayList<>();

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        pluginRegistry = loadPlugins();
        List<Class> extensionPoints = pluginRegistry.getExtensionPoints();

        for (Class extensionPoint : extensionPoints) {
            implementations = pluginRegistry.getImplementations(extensionPoint);
            LOG.debug(String.format(
                    "Found %d implementations for %s extension point: [%s]",
                    implementations.size(),
                    extensionPoint.getCanonicalName(),
                    implementations.stream().map(Class::getCanonicalName).collect(Collectors.joining(LIST_SEPARATOR))
            ));
            for (Class implementation : implementations) {
                String beanName = implementation.getCanonicalName();
                BeanDefinition beanDefinition = new RootBeanDefinition(implementation, Autowire.BY_TYPE.value(), true);
                registry.registerBeanDefinition(beanName, beanDefinition);
            }
        }

    }

    private PluginRegistry loadPlugins() {
        Path pluginsDirectory = getPluginsDirectory();
        Class[] extensionPoints = getExtensionPoints();
        String[] resourcesPatterns = getResourcesPatterns();
        LOG.info(String.format(
                "Loading plugins from %s with extension points = [%s] and resource patterns = [%s]",
                pluginsDirectory,
                Arrays.stream(extensionPoints).map(Class::getCanonicalName).collect(Collectors.joining(LIST_SEPARATOR)),
                Arrays.stream(resourcesPatterns).collect(Collectors.joining(", "))
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
                String missingDependencies = dependencyProblem.getMissingDependencies().stream()
                        .map(Dependency::toString)
                        .collect(Collectors.joining(LIST_SEPARATOR));
                LOG.error(String.format("The following dependencies are missing: [%s]", missingDependencies));
            }

            if (!dependencyProblem.getConflictingDependencies().isEmpty()) {
                String conflictingDependencies = dependencyProblem.getConflictingDependencies().stream()
                        .map(Dependency::toString)
                        .collect(Collectors.joining(LIST_SEPARATOR));
                LOG.error(String.format("This plugin conflicts with the following dependencies: [%s]", conflictingDependencies));
            }
        }
    }

    protected Path getPluginsDirectory() {
        return Paths.get(System.getProperty("user.dir"), "plugins");
    }

    private Class[] getExtensionPoints() {
        return new Class[]{
                Job.class,
                ru.meridor.steve.annotations.Job.class,
                JobCollection.class,
                Controller.class
        };
    }

    private String[] getResourcesPatterns() {
        return new String[]{
                "glob:**/*.fxml"
        };
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.addBeanPostProcessor(new PluginClassLoaderBeanPostProcessor(implementations));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<Path> resources = pluginRegistry.getResources();
        LOG.debug(String.format(
                "Found %d resource files: [%s]",
                resources.size(),
                resources.stream().map(Path::toString).collect(Collectors.joining(LIST_SEPARATOR))
        ));
        eventPublisher.publishEvent(new PluginsLoadedEvent(this, resources));
    }
}
