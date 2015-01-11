package org.meridor.erp.ui.dev;

import javafx.application.Platform;
import javafx.scene.Parent;
import org.meridor.erp.plugins.PluginsLoadedEvent;
import org.meridor.erp.ui.PluginUIProcessor;
import org.meridor.erp.ui.UIFactory;
import org.meridor.erp.ui.strategy.ReplacingUIStrategy;
import org.meridor.erp.ui.strategy.UIStrategyFactory;
import org.meridor.stecker.ResourcesWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.meridor.erp.io.StreamUtils.commaSeparated;

public class DevPluginUIProcessor extends PluginUIProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(DevPluginUIProcessor.class);

    private ResourcesWatcher resourcesWatcher;

    private final Map<Path, Parent> pluginUIMap = new HashMap<>();

    private final Map<Path, ReplacingUIStrategy<Parent>> pluginUIFactoryMap = new HashMap<>();

    public DevPluginUIProcessor(UIFactory uiFactory, Parent mainContainer) {
        super(uiFactory, mainContainer);
    }

    @Override
    public void onApplicationEvent(PluginsLoadedEvent pluginsLoadedEvent) {

        List<Path> fxmlFilePaths = getFXMLFiles(pluginsLoadedEvent);
        try {
            for (Path fxmlFilePath : fxmlFilePaths) {
                LOG.info("Using development plugin UI processor with refresh support");
                LOG.info(String.format(
                        "Processing resource file %s",
                        fxmlFilePath.toString()
                ));
                Parent ui = getUiFactory().getUI(fxmlFilePath);
                ReplacingUIStrategy<Parent> uiStrategy = UIStrategyFactory.getReplacing(getMainContainer(), ui);
                pluginUIMap.put(fxmlFilePath, ui);
                pluginUIFactoryMap.put(fxmlFilePath, uiStrategy);
                uiStrategy.add(ui);
            }
        } catch (Exception e) {
            LOG.error("An exception while initializing UI from plugin", e);
        }

        if (fxmlFilePaths.size() > 0) {
            resourcesWatcher = new ResourcesWatcher(fxmlFilePaths);
            resourcesWatcher.addChangedHandler(this::onFXMLFileChange);
            resourcesWatcher.start();
            LOG.info(String.format(
                    "Started resources watcher for paths [%s]",
                    commaSeparated(fxmlFilePaths.stream().map(Path::toString))
            ));
        } else {
            LOG.info("Did not start resources watcher because no resources were found");
        }
    }

    private void onFXMLFileChange(Path fxmlFilePath) {
        Platform.runLater(() -> {
            Optional<Parent> uiCandidate = Optional.ofNullable(pluginUIMap.get(fxmlFilePath));
            if (uiCandidate.isPresent()) {
                LOG.info(String.format(
                        "Detected change in file [%s]",
                        fxmlFilePath
                ));
                Parent oldUI = uiCandidate.get();
                try {
                    getUiFactory().reset();
                    Parent newUI = getUiFactory().getUI(fxmlFilePath);
                    Optional<ReplacingUIStrategy<Parent>> uiStrategy = Optional.ofNullable(pluginUIFactoryMap.get(fxmlFilePath));
                    if (uiStrategy.isPresent()) {
                        uiStrategy.get().replace(oldUI, newUI);
                        pluginUIMap.put(fxmlFilePath, newUI);
                        LOG.info(String.format(
                                "Successfully refreshed UI for file [%s]",
                                fxmlFilePath
                        ));
                    } else {
                        throw new IllegalStateException(String.format(
                                "Replacing UI strategy for file [%s] is not present.",
                                fxmlFilePath
                        ));
                    }
                } catch (Exception e) {
                    LOG.error(
                            String.format(
                                    "An exception while initializing UI from changed file [%s]. Ignoring these changes.",
                                    fxmlFilePath
                            ),
                            e
                    );
                }
            } else {
                LOG.error(String.format(
                        "Detected change in unknown file [%s]",
                        fxmlFilePath
                ));
            }
        });
    }

    public void onDestroy() {
        if (resourcesWatcher != null) {
            resourcesWatcher.stop();
        }
    }

}
