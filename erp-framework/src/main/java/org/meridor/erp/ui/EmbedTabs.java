package org.meridor.erp.ui;

import javafx.beans.DefaultProperty;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

@DefaultProperty("tabs")
public class EmbedTabs extends TabPane implements PluggableUI {

    private List<Tab> embeddedTabs = Collections.emptyList();

    private String to;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public void plug(Parent mainContainer) {
        embeddedTabs = new ArrayList<>(getTabs());
        Parent container = getContainer(mainContainer, getTo());
        matchContainer(container, new HashMap<Class<? extends Parent>, Consumer<Parent>>() {
            {
                put(TabPane.class, p -> {
                    TabPane pane = (TabPane) p;
                    getTabs().removeAll(embeddedTabs);
                    pane.getTabs().addAll(embeddedTabs);
                });
            }
        });
    }

    @Override
    public void unplug(Parent mainContainer) {
        Parent container = getContainer(mainContainer, getTo());
        matchContainer(container, new HashMap<Class<? extends Parent>, Consumer<Parent>>() {
            {
                put(TabPane.class, p -> {
                    TabPane pane = (TabPane) p;
                    pane.getTabs().removeAll(embeddedTabs.toArray(new Tab[embeddedTabs.size()]));
                    getTabs().addAll(embeddedTabs);
                    embeddedTabs = Collections.emptyList();
                });
            }
        });
    }
}
