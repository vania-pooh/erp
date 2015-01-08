package ru.meridor.erp.ui.strategy;

import javafx.scene.Parent;
import ru.meridor.erp.Embed;

public class UIStrategyFactory {
    
    @SuppressWarnings("unchecked")
    public static <T extends Parent> UIStrategy<T> get(Parent mainContainer, T ui) {
        if (ui instanceof Embed){
            return (UIStrategy<T>) new EmbedUIStrategy(mainContainer);
        }
        throw new UnsupportedOperationException("Unsupported UI container type");
    }
    
    public static <T extends Parent> ReplacingUIStrategy<T> getReplacing(Parent mainContainer, T ui) {
        UIStrategy<T> uiStrategy = get(mainContainer, ui);
        return new ReplacingUIStrategyDecorator<>(uiStrategy);
    }
    
}
