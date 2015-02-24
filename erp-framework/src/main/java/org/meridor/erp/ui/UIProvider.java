package org.meridor.erp.ui;

/**
 * Provides {@see PluggableUI} instances at runtime. This an alternative way to plug some UI without creating FXML files.
 * The main advantage of this approach is that you can create any complex UI you want. However the main drawback is that
 * in development mode changes in these classes can't be applied without restarting the application.
 * <p>
 * <strong>Notice:</strong> implementations are intended to have no-arg constructor otherwise they are ignored.
 */
public interface UIProvider {

    PluggableUI provide();

}
