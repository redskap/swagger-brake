package io.redskap.swagger.brake.cli;

import java.util.Collection;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.redskap.swagger.brake.cli.options.CliHelpException;
import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.cli.options.CliOptionsProvider;
import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.Starter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@SuppressFBWarnings("DM_EXIT")
public class Cli {
    private final CliOptionsProvider optionsProvider;

    public void start() {
        try {
            Options options = optionsProvider.provide();
            Collection<BreakingChange> breakingChanges = Starter.start(options);
            if (CollectionUtils.isNotEmpty(breakingChanges)) {
                System.exit(1);
            }
        } catch (CliHelpException e) {
            log.info(e.getMessage());
        } catch (Exception e) {
            log.debug("Exception occured", e);
            log.error(e.getMessage());
            log.error("For help please use " + CliOptions.getAsCliOption(CliOptions.HELP));
            System.exit(1);
        }
    }
}
