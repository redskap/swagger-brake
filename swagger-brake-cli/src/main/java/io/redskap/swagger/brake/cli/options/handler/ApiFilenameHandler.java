package io.redskap.swagger.brake.cli.options.handler;

import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.runner.Options;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ApiFilenameHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (StringUtils.isNotBlank(propertyValue)) {
            options.setApiFilename(propertyValue);
        }
    }

    @Override
    public String getHandledPropertyName() {
        return CliOptions.API_FILENAME;
    }

    @Override
    public String getHelpMessage() {
        return "Specifies the API filename Swagger Brake is going to search for";
    }
}
