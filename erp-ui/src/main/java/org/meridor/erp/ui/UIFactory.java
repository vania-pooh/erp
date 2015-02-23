package org.meridor.erp.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.meridor.erp.plugins.SmartClassLoader;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class UIFactory implements ApplicationContextAware {

    private final SmartClassLoader smartClassLoader;
    
    private ApplicationContext applicationContext;

    public UIFactory(SmartClassLoader smartClassLoader) {
        this.smartClassLoader = smartClassLoader;
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
        return getFXMLLoader(path).load();
    }
    
    private FXMLLoader getFXMLLoader(Path path) throws Exception {
        SmartFXMLLoader loader = new SmartFXMLLoader(smartClassLoader);
        loader.setControllerFactory(applicationContext::getBean);
        loader.setLocation(path.toUri().toURL());
        return loader;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
