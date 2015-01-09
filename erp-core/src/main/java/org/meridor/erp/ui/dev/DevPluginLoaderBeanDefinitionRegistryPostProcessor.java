package org.meridor.erp.ui.dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.meridor.erp.ui.PluginLoaderBeanDefinitionRegistryPostProcessor;
import org.meridor.stecker.PluginException;
import org.meridor.stecker.PluginLoader;
import org.meridor.stecker.PluginRegistry;
import org.meridor.stecker.dev.BuildToolType;
import org.meridor.stecker.dev.DevClassesScanner;
import org.meridor.stecker.dev.DevDependencyChecker;
import org.meridor.stecker.dev.DevManifestReader;
import org.meridor.stecker.dev.DevPluginsProvider;
import org.meridor.stecker.dev.DevResourcesScanner;

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
