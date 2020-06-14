package io.redskap.swagger.brake.cli.options.handler;

import io.redskap.swagger.brake.cli.options.CliOption;
import io.redskap.swagger.brake.runner.Options;

public interface CliOptionHandler {
    void handle(String optionValue, Options options);

    CliOption getHandledCliOption();

    String getHelpMessage();
}
