package ru.meridor.erp.beans.dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.meridor.erp.beans.PluginLoaderBeanDefinitionRegistryPostProcessor;
import ru.meridor.stecker.PluginException;
import ru.meridor.stecker.PluginLoader;
import ru.meridor.stecker.PluginRegistry;
import ru.meridor.stecker.dev.BuildToolType;
import ru.meridor.stecker.dev.DevClassesScanner;
import ru.meridor.stecker.dev.DevDependencyChecker;
import ru.meridor.stecker.dev.DevManifestReader;
import ru.meridor.stecker.dev.DevPluginsProvider;
import ru.meridor.stecker.dev.DevResourcesScanner;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DevPluginLoaderBeanDefinitionRegistryPostProcessor extends PluginLoaderBeanDefinitionRegistryPostProcessor {
    
    private static final Logger LOG = LoggerFactory.getLogger(DevPluginLoaderBeanDefinitionRegistryPostProcessor.class);
    
    @Override
    protected PluginRegistry loadPlugins(Path pluginsDirectory, Class[] extensionPoints, String[] resourcesPatterns) throws PluginException {
        LOG.info("Plugin development mode is enabled");
        LOG.info(String.format(
                "Plugin development directory is [%s]",
                pluginsDirectory
        ));
        return PluginLoader
                .withPluginDirectory(pluginsDirectory)
                .withPluginsProvider(new DevPluginsProvider())
                .withManifestReader(new DevManifestReader())
                .withDependencyChecker(new DevDependencyChecker())
                .withClassesScanner(new DevClassesScanner(BuildToolType.MAVEN))
                .withResourcesScanner(new DevResourcesScanner(resourcesPatterns))
                .withExtensionPoints(extensionPoints)
                .withResourcesPatterns(resourcesPatterns)
                .load();
    }

    @Override
    protected Path getPluginsDirectory() {
        String baseDirectory = System.getProperty("erp.base.dir", System.getProperty("user.dir"));
        return Paths.get(baseDirectory);
    }
}
