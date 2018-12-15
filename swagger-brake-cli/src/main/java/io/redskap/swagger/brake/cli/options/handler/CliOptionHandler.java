package io.redskap.swagger.brake.cli.options.handler;

import io.redskap.swagger.brake.runner.Options;

public interface CliOptionHandler {
    void handle(String propertyValue, Options options);

    String getHandledPropertyName();

    String getHelpMessage();
}
