package org.meridor.erp.desktop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SomeBean {
    private final static Logger LOG = LoggerFactory.getLogger(SomeBean.class);

    public SomeBean() {
        LOG.info("Instantiating SomeBean");
    }

    public void bark() {
        LOG.info("Woof!!");
    }
}
