package ru.meridor.erp.ui.strategy;

import javafx.scene.Parent;

public interface UIStrategy<T extends Parent> {
    
    void add(T ui);
    
    Parent getContainer(T ui);
        
}
