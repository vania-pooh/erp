package org.meridor.erp.ui;

import javafx.fxml.FXMLLoader;
import org.meridor.erp.plugins.SmartClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class SmartFXMLLoader extends FXMLLoader {
    
    private static final Logger LOG = LoggerFactory.getLogger(SmartFXMLLoader.class);
    
    private final SmartClassLoader smartClassLoader;

    public SmartFXMLLoader(SmartClassLoader smartClassLoader) {
        this.smartClassLoader = smartClassLoader;
        setClassLoader(smartClassLoader);
    }

    @Override
    public ClassLoader getClassLoader() {
        try {
            if (getLocation() != null) {
                Path path = Paths.get(getLocation().toURI());
                Optional<ClassLoader> classLoader = smartClassLoader.getPathClassLoader(path);
                if (classLoader.isPresent()) {
                    return classLoader.get();
                }
            } else {
                LOG.warn("Location is not set. Falling back to standard class loading logic.");
            }
        } catch (Exception e) {
            LOG.error("Failed to determine class loader", e);
        }
        return super.getClassLoader();
    }
}
