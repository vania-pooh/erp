package org.meridor.erp.command;

import io.airlift.airline.Command;
import io.airlift.airline.Option;
import io.airlift.airline.OptionType;
import org.meridor.erp.App;

import static javafx.application.Application.launch;

@Command(name = "start", description = "Start application")
public class Start extends BaseCommand {

    @Option(name = {"-d", "--dev"}, type = OptionType.COMMAND, description = "Whether to enable development mode")
    public boolean isDevelopmentMode;

    @Option(name = {"--no-splash-screen"}, type = OptionType.COMMAND, description = "Whether to hide splash screen (useful for development mode)")
    private boolean hideSplashScreen;

    @Override
    public void runUnsafe() throws Exception {
        App.setIsDevelopmentMode(isDevelopmentMode);
        App.setHideSplashScreen(hideSplashScreen);
        launch(App.class);
    }

}
