package ru.meridor.erp.beans;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;

public class UIFactory implements ApplicationContextAware {

    private final FXMLLoader fxmlLoader;

    private ApplicationContext applicationContext;

    public UIFactory(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    private Optional<URL> getURLFromClassPath(String fileName) throws IOException {
        Resource resource = applicationContext.getResource(fileName);
        return resource.exists() ?
                Optional.of(resource.getURL()) :
                Optional.empty();
    }

    public Parent getUI(URL url) throws Exception {
        try (InputStream inputStream = url.openStream()) {
            return fxmlLoader.load(inputStream);
        }
    }

    public Parent getUI(String fileName) throws Exception {
        Optional<URL> fxml = getURLFromClassPath(fileName);
        if (!fxml.isPresent()) {
            throw new FileNotFoundException(String.format("FXML file %s does not exist", fileName));
        }
        return getUI(fxml.get());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
