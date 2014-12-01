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

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Optional;

public class Main extends Application {

    private Parent mainContainer;

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
         * 3)
         */
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        AbstractXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/context.xml");
        context.registerShutdownHook();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello World");
        Optional<URL> fxml = getURLFromClassPath("main.fxml");
        if (!fxml.isPresent()) {
            throw new FileNotFoundException("Can't find main GUI configuration file");
        }
        mainContainer = FXMLLoader.load(fxml.get());

        insertPluginComponents(getURLFromClassPath("plugin.fxml"));

        Scene scene = new Scene(mainContainer);
        stage.setScene(scene);
        stage.show();
    }

    private Optional<URL> getURLFromClassPath(String fileName) {
        return (getClass().getClassLoader() != null) ?
                Optional.ofNullable(getClass().getClassLoader().getResource(fileName)) :
                Optional.empty();
    }

    private void insertPluginComponents(Optional<URL> pluginUrl) throws Exception {
        if (pluginUrl.isPresent()) {
            Parent pluginContents = FXMLLoader.load(pluginUrl.get());
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
