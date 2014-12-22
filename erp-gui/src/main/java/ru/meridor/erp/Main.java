package ru.meridor.erp;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main extends Application {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

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
         * 6. Testing - Arabica
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
        LOG.info("Initializing application");
        super.init();
        processParameters();
    }

    private void processParameters() {
        Parameters parameters = getParameters();
        //TODO: define parameters
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello World");
        initContext();
        Parent mainContainer = context.getBean("mainContainer", Parent.class);
        Scene scene = new Scene(mainContainer);
        stage.setScene(scene);
        stage.show();
    }

    private void initContext() {
        context = new ClassPathXmlApplicationContext("META-INF/spring/context.xml");
        context.registerShutdownHook();
    }

}
