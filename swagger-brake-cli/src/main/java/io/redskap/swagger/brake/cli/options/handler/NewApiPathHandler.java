package io.redskap.swagger.brake.cli.options.handler;

import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.runner.Options;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
class NewApiPathHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (StringUtils.isBlank(propertyValue)) {
            throw new IllegalArgumentException(CliOptions.getAsCliOption(getHandledPropertyName()) + " must be set");
        }
        options.setNewApiPath(propertyValue);
    }

    @Override
    public String getHandledPropertyName() {
        return CliOptions.NEW_API_PATH;
    }

    @Override
    public String getHelpMessage() {
        return "The absolute path of the new api file";
    }
}