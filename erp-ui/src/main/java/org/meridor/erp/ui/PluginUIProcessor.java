package org.meridor.erp.ui;

import javafx.scene.Parent;
import org.meridor.erp.io.ResourceCategory;
import org.meridor.erp.plugins.PluginsLoadedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PluginUIProcessor implements ApplicationListener<PluginsLoadedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(PluginUIProcessor.class);

    private final UIFactory uiFactory;

    private final Parent mainContainer;

    public PluginUIProcessor(UIFactory uiFactory, Parent mainContainer) {
        this.uiFactory = uiFactory;
        this.mainContainer = mainContainer;
    }

    protected UIFactory getUiFactory() {
        return uiFactory;
    }

    @Override
    public void onApplicationEvent(PluginsLoadedEvent pluginsLoadedEvent) {
        List<Path> fxmlFilePaths = getFXMLFiles(pluginsLoadedEvent);
        processFXMLFiles(fxmlFilePaths);
    }

    protected Map<Path, PluggableUI> processFXMLFiles(List<Path> fxmlFilePaths) {
        Map<Path, PluggableUI> pluggedUI = new HashMap<>();
        try {
            for (Path fxmlFilePath : fxmlFilePaths) {
                LOG.debug(String.format(
                        "Processing resource file %s",
                        fxmlFilePath.toString()
                ));
                Optional<PluggableUI> pluggableUI = processFileContents(fxmlFilePath);
                if (pluggableUI.isPresent()) {
                    pluggedUI.put(fxmlFilePath, pluggableUI.get());
                    pluggableUI.get().plug(mainContainer);
                }
            }
        } catch (Exception e) {
            LOG.error("An exception while initializing UI from plugin", e);
        }
        return pluggedUI;
    }
    
    protected Optional<PluggableUI> processFileContents(Path fxmlFilePath) throws Exception {
        Parent ui = getUiFactory().getUI(fxmlFilePath);
        if (ui instanceof PluggableUI) {
            PluggableUI pluggableUI = (PluggableUI) ui;
            return Optional.of(pluggableUI);
        } else {
            LOG.error(String.format(
                    "Ignoring resource file %s as it does not contain pluggable UI element",
                    fxmlFilePath.toString()
            ));
            return Optional.empty();
        }
    }
    
    protected Parent getMainContainer() {
        return mainContainer;
    }

    protected static List<Path> getFXMLFiles(PluginsLoadedEvent pluginsLoadedEvent) {
        return pluginsLoadedEvent.getPluginRegistry().getResources().stream()
                .filter(path -> ResourceCategory.FXML_FILE.getPathMatcher().matches(path))
                .collect(Collectors.toList());
    }

}
