package io.redskap.swagger.brake.cli.options.handler;

import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.runner.Options;
import org.springframework.stereotype.Component;

@Component
public class UploadEnabledHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (propertyValue == null) {
            return;
        }

        if (propertyValue.isEmpty()) {
            options.setUploadEnabled(true);
        }
    }

    @Override
    public String getHandledPropertyName() {
        return CliOptions.UPLOAD_ENABLED;
    }

    @Override
    public String getHelpMessage() {
        return "Determines whether uploading is needed for an api-server";
    }
}
