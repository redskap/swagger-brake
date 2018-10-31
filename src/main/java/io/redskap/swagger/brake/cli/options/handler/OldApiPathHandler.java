package io.redskap.swagger.brake.cli.options.handler;

import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.runner.Options;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
class OldApiPathHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (StringUtils.isBlank(propertyValue)) {
            throw new IllegalStateException(CliOptions.getAsCliOption(getHandledPropertyName()) + " must be set");
        }
        options.setOldApiPath(propertyValue);
    }

    @Override
    public String getHandledPropertyName() {
        return CliOptions.OLD_API_PATH;
    }

    @Override
    public String getHelpMessage() {
        return "The absolute path of the old api file";
    }
}
