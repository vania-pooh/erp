package ru.meridor.erp.beans;

import javafx.scene.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import ru.meridor.erp.beans.ui.UIStrategyFactory;

import java.nio.file.Path;

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
        try {
            for (Path fxmlFilePath : pluginsLoadedEvent.getFxmlFiles()) {
                LOG.debug(String.format(
                        "Processing resource file %s",
                        fxmlFilePath.toString()
                ));
                Parent ui = getUiFactory().getUI(fxmlFilePath);
                UIStrategyFactory.get(mainContainer, ui).add(ui);
            }
        } catch (Exception e) {
            LOG.error("An exception while initializing UI from plugin", e);
        }
    }

    protected Parent getMainContainer() {
        return mainContainer;
    }

}
