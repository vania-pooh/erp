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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class UIFactory implements ApplicationContextAware {

    private final FXMLLoader fxmlLoader;

    private ApplicationContext applicationContext;

    public UIFactory(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    private Optional<Path> getPathFromClassPath(String fileName) throws IOException {
        Resource resource = applicationContext.getResource(fileName);

        return resource.exists() ?
                Optional.of(Paths.get(resource.getURI())) :
                Optional.empty();
    }
    public Parent getUI(String fileName) throws Exception {
        Optional<Path> fxml = getPathFromClassPath(fileName);
        if (!fxml.isPresent()) {
            throw new FileNotFoundException(String.format("FXML file %s does not exist", fileName));
        }
        return getUI(fxml.get());
    }

    public Parent getUI(Path path) throws Exception {
        try (InputStream inputStream = Files.newInputStream(path)) {
            return fxmlLoader.load(inputStream);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
