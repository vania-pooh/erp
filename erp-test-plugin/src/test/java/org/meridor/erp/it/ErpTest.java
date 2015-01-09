package org.meridor.erp.it;

import org.meridor.arabica.JavaFXTest;
import org.meridor.erp.Main;

public abstract class ErpTest extends JavaFXTest<Main> {

    @Override
    public Class<Main> getApplicationClass() {
        return Main.class;
    }
}
