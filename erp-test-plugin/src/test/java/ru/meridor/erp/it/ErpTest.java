package ru.meridor.erp.it;

import org.meridor.arabica.JavaFXTest;
import ru.meridor.erp.Main;

public abstract class ErpTest extends JavaFXTest<Main> {

    @Override
    public Class<Main> getApplicationClass() {
        return Main.class;
    }
}
