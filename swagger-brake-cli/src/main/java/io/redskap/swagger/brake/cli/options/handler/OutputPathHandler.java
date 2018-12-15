package io.redskap.swagger.brake.cli.options.handler;

import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.runner.Options;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class OutputPathHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (StringUtils.isNotBlank(propertyValue)) {
            options.setOutputFilePath(propertyValue);
        }
    }

    @Override
    public String getHandledPropertyName() {
        return CliOptions.OUTPUT_PATH;
    }

    @Override
    public String getHelpMessage() {
        return "Specifies the folder for the output. Only applicable in case of file output formats.";
    }
}
