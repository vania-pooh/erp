package org.meridor.erp.ui.dev;

import javafx.application.Platform;
import javafx.scene.Parent;
import org.meridor.erp.plugins.PluginsLoadedEvent;
import org.meridor.erp.ui.PluggableUI;
import org.meridor.erp.ui.PluginUIProcessor;
import org.meridor.erp.ui.UIFactory;
import org.meridor.stecker.ResourcesWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.meridor.erp.io.StreamUtils.commaSeparated;

public class DevPluginUIProcessor extends PluginUIProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(DevPluginUIProcessor.class);

    private ResourcesWatcher resourcesWatcher;

    private final Map<Path, PluggableUI> pluggedUIMap = new HashMap<>();

    public DevPluginUIProcessor(UIFactory uiFactory, Parent mainContainer) {
        super(uiFactory, mainContainer);
    }

    @Override
    public void onApplicationEvent(PluginsLoadedEvent pluginsLoadedEvent) {

        LOG.info("Using development plugin UI processor with refresh support");
        List<Path> fxmlFilePaths = getFXMLFiles(pluginsLoadedEvent);
        Map<Path, PluggableUI> pluggedUI = processFXMLFiles(fxmlFilePaths);
        pluggedUIMap.putAll(pluggedUI);

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
            Optional<PluggableUI> uiCandidate = Optional.ofNullable(pluggedUIMap.get(fxmlFilePath));
            if (uiCandidate.isPresent()) {
                LOG.info(String.format(
                        "Detected change in file [%s]",
                        fxmlFilePath
                ));
                PluggableUI oldUI = uiCandidate.get();
                try {
                    getUiFactory().reset();
                    Optional<PluggableUI> newUI = processFileContents(fxmlFilePath);
                    if (newUI.isPresent()) {
                        oldUI.unplug(getMainContainer());
                        newUI.get().plug(getMainContainer());
                        pluggedUIMap.put(fxmlFilePath, newUI.get());
                        LOG.info(String.format(
                                "Successfully refreshed UI for file [%s]",
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
