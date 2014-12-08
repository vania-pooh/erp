package ru.meridor.erp.beans;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import ru.meridor.erp.Embed;

import java.nio.file.Path;
import java.util.Optional;

public class PluginUIProcessor implements ApplicationListener<PluginsLoadedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(PluginUIProcessor.class);

    private final UIFactory uiFactory;

    private final Parent mainContainer;

    public PluginUIProcessor(UIFactory uiFactory, Parent mainContainer) {
        this.uiFactory = uiFactory;
        this.mainContainer = mainContainer;
    }

    private void embedUI(Path fxmlPath) throws Exception {
        Parent pluginContents = uiFactory.getUI(fxmlPath);
        if (pluginContents instanceof Embed) {
            Embed embed = (Embed) pluginContents;
            ObservableList<Node> children = embed.getChildren();
            String containerId = embed.getTo();
            Optional<Node> container = Optional.ofNullable(mainContainer.lookup("#" + containerId));
            if (container.isPresent() && container.get() instanceof Pane){
                Pane pane = (Pane) container.get();
                pane.getChildren().addAll(children);
            }
        }
    }

    @Override
    public void onApplicationEvent(PluginsLoadedEvent pluginsLoadedEvent) {
        try {
            for (Path fxmlFilePath : pluginsLoadedEvent.getFxmlFiles()) {
                LOG.debug(String.format(
                        "Processing resource file %s",
                        fxmlFilePath.toString()
                ));
                embedUI(fxmlFilePath);
            }
        } catch (Exception e) {
            LOG.error("An exception while initializing UI from plugin", e);
        }
    }
}
