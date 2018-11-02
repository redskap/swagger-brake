package io.redskap.swagger.brake.cli;

import io.redskap.swagger.brake.cli.options.CliHelpException;
import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.cli.options.CliOptionsProvider;
import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.Runner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Cli {
    private final CliOptionsProvider optionsProvider;
    private final Runner executor;

    public void start() {
        try {
            Options options = optionsProvider.provide();
            executor.run(options);
        } catch (CliHelpException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("For help please use " + CliOptions.getAsCliOption(CliOptions.HELP));
        }
    }
}
