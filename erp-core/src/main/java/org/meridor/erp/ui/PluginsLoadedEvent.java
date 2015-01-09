package org.meridor.erp.ui;

import org.springframework.context.ApplicationEvent;

import java.nio.file.Path;
import java.util.List;

public class PluginsLoadedEvent extends ApplicationEvent {

    private List<Path> fxmlFiles;

    public PluginsLoadedEvent(Object source, List<Path> fxmlFiles) {
        super(source);
        this.fxmlFiles = fxmlFiles;
    }

    public List<Path> getFxmlFiles() {
        return fxmlFiles;
    }
}
