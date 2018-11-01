package io.redskap.swagger.brake.cli;

import java.util.Collection;

import io.redskap.swagger.brake.cli.options.CliHelpException;
import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.cli.options.CliOptionsProvider;
import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.report.ReporterFactory;
import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.Runner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Cli {
    private final CliOptionsProvider optionsProvider;
    private final Runner executor;
    private final ReporterFactory reporterFactory;

    public void start() {
        try {
            Options options = optionsProvider.provide();
            Collection<BreakingChange> breakingChanges = executor.run(options);
            reporterFactory.create(options).report(breakingChanges);
        } catch (CliHelpException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("For help please use " + CliOptions.getAsCliOption(CliOptions.HELP));
        }
    }
}
