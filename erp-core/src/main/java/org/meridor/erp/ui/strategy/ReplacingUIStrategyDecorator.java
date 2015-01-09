package org.meridor.erp.ui.strategy;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplacingUIStrategyDecorator<T extends Parent> implements ReplacingUIStrategy<T> {
    
    private final UIStrategy<T> uiStrategy;
    
    private final Map<T, List<Node>> uiNodes = new HashMap<>();

    public ReplacingUIStrategyDecorator(UIStrategy<T> uiStrategy) {
        this.uiStrategy = uiStrategy;
    }

    @Override
    public void add(T ui) {
        uiNodes.put(ui, new ArrayList<>(ui.getChildrenUnmodifiable()));
        uiStrategy.add(ui);
    }

    @Override
    public Parent getContainer(T ui) {
        return uiStrategy.getContainer(ui);
    }

    @Override
    public void replace(T oldUI, T newUI) {
        List<Node> nodes = uiNodes.remove(oldUI);
        if (nodes == null) {
            nodes = Collections.emptyList();
        }
        Parent container = getContainer(oldUI);
        if (container instanceof Pane) {
            ((Pane) container).getChildren().removeAll(nodes.toArray(new Node[nodes.size()]));
        }
        add(newUI);
    }
}
