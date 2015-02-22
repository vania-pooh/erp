package org.meridor.erp.it;

import org.meridor.arabica.JavaFXTest;
import org.meridor.erp.App;
import org.meridor.erp.Main;

public abstract class ErpTest extends JavaFXTest<App> {

    @Override
    public Class<App> getApplicationClass() {
        return App.class;
    }
}
