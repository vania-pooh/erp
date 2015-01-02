package ru.meridor.erp.beans.ui;

import javafx.scene.Parent;

public interface UIStrategy<T extends Parent> {
    
    void add(T ui);
    
    Parent getContainer(T ui);
        
}
