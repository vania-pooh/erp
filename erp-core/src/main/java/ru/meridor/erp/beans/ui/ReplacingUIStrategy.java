package ru.meridor.erp.beans.ui;

import javafx.scene.Parent;

public interface ReplacingUIStrategy<T extends Parent> extends UIStrategy<T> {
    
    void replace(T oldUI, T newUI);
    
}
