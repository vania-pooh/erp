package org.meridor.erp.ui;

import javafx.beans.DefaultProperty;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

@DefaultProperty("children")
public class Embed extends Pane implements PluggableUI {
    
    private List<Node> embeddedNodes = Collections.emptyList();
    
    private String to;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public void plug(Parent mainContainer) {
        embeddedNodes = new ArrayList<>(getChildren());
        Parent container = getContainer(mainContainer, getTo());
        matchContainer(container, new HashMap<Class<? extends Parent>, Consumer<Parent>>() {
            {
                put(Pane.class, p -> {
                    Pane pane = (Pane) p;
                    getChildren().removeAll(embeddedNodes);
                    pane.getChildren().addAll(embeddedNodes);
                });
            }
        });
    }

    @Override
    public void unplug(Parent mainContainer) {
        Parent container = getContainer(mainContainer, getTo());
        matchContainer(container, new HashMap<Class<? extends Parent>, Consumer<Parent>>() {
            {
                put(Pane.class, p -> {
                    Pane pane = (Pane) p;
                    pane.getChildren().removeAll(embeddedNodes.toArray(new Node[embeddedNodes.size()]));
                    getChildren().addAll(embeddedNodes);
                    embeddedNodes = Collections.emptyList();
                });
            }
        });
    }
}
