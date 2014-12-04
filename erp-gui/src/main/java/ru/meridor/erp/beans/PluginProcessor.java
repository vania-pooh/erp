package ru.meridor.erp.beans;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import ru.meridor.erp.Embed;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PluginProcessor implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(PluginProcessor.class);

    //TODO: create controller instances from plugin file here and inject other extension points via BeanDefinitionRegistryPostProcessor
    //TODO: plugin name to FXML list mapping (to be filled on first plugin pass)
    private final Map<String, List<Resource>> fxmlFiles = new HashMap<>();

    private final UIFactory uiFactory;

    private final Parent mainContainer;

    public PluginProcessor(UIFactory uiFactory, Parent mainContainer) {
        this.uiFactory = uiFactory;
        this.mainContainer = mainContainer;
    }

    private void embedUI() throws Exception {
        String fileName = "plugin.fxml"; //TODO: this should come from plugin classpath
        Parent pluginContents = uiFactory.getUI(fileName);
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
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            embedUI();
        } catch (Exception e) {
            LOG.error("An exception while initializing UI from plugin", e);
        }
    }
}
