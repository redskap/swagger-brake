package io.redskap.swagger.brake.cli;

import static java.lang.System.out;

import java.util.Collection;

import io.redskap.swagger.brake.cli.options.CliHelpException;
import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.cli.options.CliOptionsProvider;
import io.redskap.swagger.brake.core.BreakingChange;
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
            Collection<BreakingChange> breakingChanges = executor.run(options);
            if (!breakingChanges.isEmpty()) {
                System.out.println("There were breaking API changes");
                System.out.println();
                breakingChanges.stream().map(BreakingChange::getMessage).forEach(out::println);
            } else {
                System.out.println("No breaking API changes detected");
            }
        } catch (CliHelpException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("For help please use " + CliOptions.getAsCliOption(CliOptions.HELP));
        }
    }
}
