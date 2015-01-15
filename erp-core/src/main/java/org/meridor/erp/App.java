package org.meridor.erp;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.meridor.erp.startup.BeanStatus;
import org.meridor.erp.startup.StartupStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Optional;

public class App extends Application {

    /**
     * Technologies:
     * -------------
     * 1. Help System - Java Help
     * 2. GUI - JavaFX (fxml files + Java code)
     * 3. Running jobs - Steve
     * 4. Plugin system - Stecker
     * 5. Logger - log4j
     * 6. Testing - Arabica
     * <p>
     * <p>
     * Here come main application loading steps:
     * 1) Show splash screen
     * 2) Start Spring container with Steve support
     * 3) Initialize plugins and inject respective controller beans to Spring
     * 4) Embed GUI elements to main UI
     */

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    private AbstractXmlApplicationContext context;

    private static boolean isDevelopmentMode;
    private static boolean hideSplashScreen;

    private static final int SPLASH_WIDTH = 600;
    private static final int SPLASH_HEIGHT = 200;

    private Pane splashScreenLayout;
    private ProgressBar progressBar;
    private Label progressBarComment;

    @Override
    public void init() throws Exception {
        LOG.info("Initializing application");
        super.init();
        initSplashScreenLayout();
    }

    @Override
    public void start(Stage stage) throws Exception {
        if (!hideSplashScreen) {
            startWithSplashScreen(
                    stage,
                    () -> showMainStage(Optional.empty())
            );
        } else {
            startApplication();
            showMainStage(Optional.of(stage));
        }
    }

    private void initContext() {
        context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/context.xml"}, false);
        if (isDevelopmentMode) {
            LOG.info("Development mode is enabled");
            context.getEnvironment().setActiveProfiles("dev");
        }
        context.registerShutdownHook();
        context.refresh();
    }

    private void initSplashScreenLayout() {
        progressBar = new ProgressBar();
        progressBar.setPrefWidth(SPLASH_WIDTH - 20);
        progressBarComment = new Label("Loading application...");
        splashScreenLayout = new VBox();
        splashScreenLayout.getChildren().addAll(progressBar, progressBarComment);
        progressBarComment.setAlignment(Pos.CENTER);
        splashScreenLayout.setEffect(new DropShadow());
    }

    private void showMainStage(Optional<Stage> externalStage) {
        Stage stage = externalStage.isPresent() ? externalStage.get() : new Stage(StageStyle.DECORATED);
        stage.setTitle("Hello World");
        Parent mainContainer = context.getBean("mainContainer", Parent.class);
        Scene scene = new Scene(mainContainer);
        stage.setScene(scene);
        stage.show();
    }

    private void startWithSplashScreen(
            Stage primaryStage,
            InitCompletionHandler onInitializationSuccess
    ) {

        Task<Void> statusChangeTask = getStatusChangeTask();
        progressBarComment.textProperty().bind(statusChangeTask.messageProperty());
        progressBar.progressProperty().bind(statusChangeTask.progressProperty());

        final Thread statusChangeThread = new Thread(statusChangeTask);
        Task<Void> startTask = new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {
                startApplication();
                statusChangeThread.join();
                return null;
            }
        };
        final Thread startTaskThread = new Thread(startTask);

        startTask.stateProperty().addListener((observableValue, oldState, newState) -> {
            switch (newState) {
                case SUCCEEDED: {
                    onLoadingSucceeded(primaryStage, onInitializationSuccess);
                    break;
                }
                case FAILED: {
                    onLoadingFailed(primaryStage, startTask.getException());
                    break;
                }
            }
        });

        showSplashScreen(primaryStage);
        statusChangeThread.start();
        startTaskThread.start();
    }

    private void showSplashScreen(Stage primaryStage) {
        Scene splashScene = new Scene(splashScreenLayout);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        primaryStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        primaryStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        primaryStage.setScene(splashScene);
        primaryStage.show();
    }

    private void hideSplashScreen(Stage primaryStage) {
        progressBar.progressProperty().unbind();
        progressBar.setProgress(1);
        primaryStage.toFront();
        FadeTransition fadeSplash = new FadeTransition(Duration.seconds(0.4), splashScreenLayout);
        fadeSplash.setFromValue(1.0);
        fadeSplash.setToValue(0.0);
        fadeSplash.setOnFinished(actionEvent -> primaryStage.hide());
        fadeSplash.play();
    }

    private void onLoadingSucceeded(Stage primaryStage, InitCompletionHandler onInitializationSuccess) {
        hideSplashScreen(primaryStage);
        onInitializationSuccess.onInitializationComplete();
    }

    private void onLoadingFailed(Stage primaryStage, Throwable e) {
        hideSplashScreen(primaryStage);
        LOG.error("Failed to start application", e);
        //TODO: we may also want to show some dialog box here
        if (context != null && context.isRunning()) {
            context.stop();
        }
    }

    private Task<Void> getStatusChangeTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {
                while (StartupStatus.hasMoreTasks()) {
                    Optional<BeanStatus> beanStatus = StartupStatus.getNextTask();
                    if (beanStatus.isPresent()) {
                        updateMessage(beanStatus.get().getMessage());
                        updateProgress(beanStatus.get().getProgress(), 1d);
                    }
                    Thread.sleep(300);
                }
                return null;
            }
        };
    }

    private void startApplication() {
        initContext();
    }

    public static void setIsDevelopmentMode(boolean isDevelopmentMode) {
        App.isDevelopmentMode = isDevelopmentMode;
    }

    public static void setHideSplashScreen(boolean hideSplashScreen) {
        App.hideSplashScreen = hideSplashScreen;
    }

    private interface InitCompletionHandler {
        public void onInitializationComplete();
    }
}
