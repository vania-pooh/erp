package ru.meridor.erp.beans;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.*;
import java.util.Collections;

public class UIFactory implements ApplicationContextAware {

    private final FXMLLoader fxmlLoader;

    private ApplicationContext applicationContext;

    public UIFactory(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public Parent getUI(String fileName) throws Exception {
        Resource resource = applicationContext.getResource(fileName);

        URI uri = resource.getURI();
        String scheme = uri.getScheme();
        if (scheme.equals("jar")) {
            try (FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
                Path path = fileSystem.getPath(fileName);
                if (Files.exists(path)) {
                    return getUI(path);
                }
            }
        } else if (scheme.equals("file")) {
            Path path = Paths.get(uri);
            if (Files.exists(path)) {
                return getUI(path);
            }
        }

        throw new FileNotFoundException(String.format("FXML file %s does not exist", fileName));
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
