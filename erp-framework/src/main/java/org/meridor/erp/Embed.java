package org.meridor.erp;

import javafx.beans.DefaultProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;

@DefaultProperty("children")
public class Embed extends Parent {

    //TODO: we can add properties like - after, before, last, first

    private String to;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public ObservableList<Node> getChildren() {
        return super.getChildren();
    }
}
