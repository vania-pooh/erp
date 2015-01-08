package ru.meridor.erp.ui;

import javafx.fxml.FXMLLoader;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class FXMLLoaderFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public FXMLLoader getFXMLLoader(UberClassLoader uberClassLoader) {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(applicationContext::getBean);
        loader.setClassLoader(uberClassLoader);
        return loader;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
