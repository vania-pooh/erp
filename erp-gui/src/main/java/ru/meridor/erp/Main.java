package ru.meridor.erp;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;

public class Main extends Application {

    private Parent mainContainer;

    private AbstractXmlApplicationContext context;

    public static void main(String[] args) throws Exception {
        /**
         * Technologies:
         * -------------
         * 1. Help System - Java Help
         * 2. GUI - JavaFX (fxml files + Java code)
         * 3. Running jobs - Steve
         * 4. Plugin system - Stecker
         * 5. Logger - log4j
         * 6.
         *
         *
         * Here come main application loading steps:
         * 1) Show splash screen
         * 2) Start Spring container with Steve support
         * 3) Initialize plugins and inject respective controller beans to Spring
         * 4) Embed GUI elements to main UI
         */
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        context = new ClassPathXmlApplicationContext("META-INF/spring/context.xml");
        context.registerShutdownHook();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello World");
        stage.setMaximized(true);
        Optional<URL> fxml = getURLFromClassPath("main.fxml");
        if (!fxml.isPresent()) {
            throw new FileNotFoundException("Can't find main GUI configuration file");
        }
        mainContainer = loadFXML(fxml.get());

        insertPluginComponents(getURLFromClassPath("plugin.fxml"));

        Scene scene = new Scene(mainContainer);
        stage.setScene(scene);
        stage.show();
    }

    private Optional<URL> getURLFromClassPath(String fileName) throws IOException {
        Resource resource = context.getResource(fileName);
        return resource.exists() ?
                Optional.of(resource.getURL()) :
                Optional.empty();
    }

    private Parent loadFXML(URL url) throws Exception {
        FXMLLoader loader = getFXMLLoader();
        try (InputStream inputStream = url.openStream()) {
            return loader.load(inputStream);
        }
    }

    private FXMLLoader getFXMLLoader() {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(context::getBean);
        return loader;
    }

    private void insertPluginComponents(Optional<URL> pluginUrl) throws Exception {
        if (pluginUrl.isPresent()) {
            Parent pluginContents = loadFXML(pluginUrl.get());
            if (pluginContents instanceof Embed) {
                Embed embed = (Embed) pluginContents;
                ObservableList<Node> children = embed.getChildren();
                String containerId = embed.getTo();
                Optional<Node> container = Optional.ofNullable(mainContainer.lookup("#" + containerId));
                if (container.isPresent() && container.get() instanceof Pane){
                    Pane pane = (Pane) container.get();
                    pane.getChildren().addAll(children);
                }
            }
        }
    }

}
