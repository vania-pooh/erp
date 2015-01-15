package org.meridor.erp;

import io.airlift.airline.Cli;
import io.airlift.airline.Cli.CliBuilder;
import io.airlift.airline.Help;
import io.airlift.airline.ParseException;
import org.meridor.erp.command.Start;
import org.meridor.erp.command.Version;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            CliBuilder<Runnable> builder = Cli.<Runnable>builder("erp")
                    .withDescription("ERP Launcher")
                    .withDefaultCommand(Help.class)
                    .withCommand(Version.class)
                    .withCommand(Start.class);
            Cli<Runnable> parser = builder.build();
            parser.parse(args).run();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

}
