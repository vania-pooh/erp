package org.meridor.erp.command;

import org.meridor.erp.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseCommand implements Runnable {

    protected static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public void run() {
        try {
            runUnsafe();
        } catch (Exception e) {
            LOG.error("An exception while running command", e);
        }
    }

    public abstract void runUnsafe() throws Exception;

}
