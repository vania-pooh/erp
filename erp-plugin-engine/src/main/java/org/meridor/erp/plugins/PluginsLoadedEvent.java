package org.meridor.erp.plugins;

import org.meridor.stecker.PluginRegistry;
import org.springframework.context.ApplicationEvent;

public class PluginsLoadedEvent extends ApplicationEvent {

    private final PluginRegistry pluginRegistry;

    public PluginsLoadedEvent(Object source, PluginRegistry pluginRegistry) {
        super(source);
        this.pluginRegistry = pluginRegistry;
    }

    public PluginRegistry getPluginRegistry() {
        return pluginRegistry;
    }
}
