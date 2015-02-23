package org.meridor.erp.ui;

import javafx.fxml.FXMLLoader;
import org.meridor.erp.plugins.SmartClassLoader;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class FXMLLoaderFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public FXMLLoader getFXMLLoader(SmartClassLoader smartClassLoader) {
        SmartFXMLLoader loader = new SmartFXMLLoader(smartClassLoader);
        loader.setControllerFactory(applicationContext::getBean);
        return loader;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
