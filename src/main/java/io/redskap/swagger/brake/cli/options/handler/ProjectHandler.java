package io.redskap.swagger.brake.cli.options.handler;

import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.runner.Options;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ProjectHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (!StringUtils.isBlank(propertyValue)) {
            options.setProject(propertyValue);
        }
    }

    @Override
    public String getHandledPropertyName() {
        return CliOptions.PROJECT;
    }

    @Override
    public String getHelpMessage() {
        return "The project name which will be used for uploading the data";
    }
}
