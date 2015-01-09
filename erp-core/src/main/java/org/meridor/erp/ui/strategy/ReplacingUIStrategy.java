package org.meridor.erp.ui.strategy;

import javafx.scene.Parent;

public interface ReplacingUIStrategy<T extends Parent> extends UIStrategy<T> {
    
    void replace(T oldUI, T newUI);
    
}
