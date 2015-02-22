package org.meridor.erp.ui;

import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public interface PluggableUI {
    
    void plug(Parent mainContainer);
    
    void unplug(Parent mainContainer);
    
    default Parent getContainer(Parent mainContainer, String containerId) {
        Optional<Node> node = Optional.ofNullable(mainContainer.lookup("#" + containerId));
        return (node.isPresent() && node.get() instanceof Parent) ?
                (Parent) node.get() :
                mainContainer;
    }
    
    default void matchContainer(Parent container, Map<Class<? extends Parent>, Consumer<Parent>> matchers) {
        Class<? extends Parent> containerClass = container.getClass();
        Optional<Class<? extends Parent>> matchingClass = Optional.empty(); 
        if (matchers.containsKey(containerClass)) {
            matchingClass = Optional.of(containerClass);
        } else {
            for (Class<? extends Parent> matchingClassCandidate : matchers.keySet()) {
                if (matchingClassCandidate.isAssignableFrom(containerClass)) {
                    matchingClass = Optional.of(matchingClassCandidate);
                    break;
                }
            }
        }
        if (!matchingClass.isPresent()) {
            throw new UnsupportedOperationException(String.format("Unsupported container type %s", containerClass.getCanonicalName()));
        }
        matchers.get(matchingClass.get()).accept(container);
    }
    
}
