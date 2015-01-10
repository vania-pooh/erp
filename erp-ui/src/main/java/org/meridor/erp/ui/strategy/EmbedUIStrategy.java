package org.meridor.erp.ui.strategy;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import org.meridor.erp.Embed;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmbedUIStrategy implements UIStrategy<Embed> {

    private final Parent mainContainer;

    public EmbedUIStrategy(Parent mainContainer) {
        this.mainContainer = mainContainer;
    }

    @Override
    public void add(Embed ui) {
        List<Node> children = new ArrayList<>(ui.getChildren());
        Node container = getContainer(ui);
        if (container instanceof Pane) {
            Pane pane = (Pane) container;
            ui.getChildren().removeAll(children);
            pane.getChildren().addAll(children);
        }
    }

    @Override
    public Parent getContainer(Embed ui) {
        Optional<Node> node = Optional.ofNullable(mainContainer.lookup("#" + ui.getTo()));
        return (node.isPresent() && node.get() instanceof Parent) ?
                (Parent) node.get() :
                mainContainer;
    }

}
