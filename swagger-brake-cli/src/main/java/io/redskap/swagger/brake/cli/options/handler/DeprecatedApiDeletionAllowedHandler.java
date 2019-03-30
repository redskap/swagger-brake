package io.redskap.swagger.brake.cli.options.handler;

import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.runner.Options;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DeprecatedApiDeletionAllowedHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (StringUtils.isNotBlank(propertyValue)) {
            options.setDeprecatedApiDeletionAllowed(BooleanUtils.toBooleanObject(propertyValue));
        }
    }

    @Override
    public String getHandledPropertyName() {
        return CliOptions.DEPRECATED_API_DELETION_ALLOWED;
    }

    @Override
    public String getHelpMessage() {
        return "Whether to allow the deletion of deprecated APIs. Defaults to true.";
    }
}
