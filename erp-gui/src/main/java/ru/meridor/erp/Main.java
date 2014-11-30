package ru.meridor.erp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Optional;

public class Main extends Application {

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
         * 3)
         */
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        context = new ClassPathXmlApplicationContext("META-INF/spring/context.xml");
        context.start();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello World");
        Optional<URL> fxml = getFXML("main.fxml");
        if (!fxml.isPresent()) {
            throw new FileNotFoundException("Can't find main GUI configuration file");
        }
        Parent root = FXMLLoader.load(fxml.get());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private Optional<URL> getFXML(String fileName) {
        return (getClass().getClassLoader() != null) ?
                Optional.ofNullable(getClass().getClassLoader().getResource(fileName)) :
                Optional.empty();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        context.stop();
        context.destroy();
    }
}
