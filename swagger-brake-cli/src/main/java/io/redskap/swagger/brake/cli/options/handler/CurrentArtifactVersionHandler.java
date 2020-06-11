package io.redskap.swagger.brake.cli.options.handler;

import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.runner.Options;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CurrentArtifactVersionHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (StringUtils.isNotBlank(propertyValue)) {
            options.setCurrentArtifactVersion(propertyValue);
        }
    }

    @Override
    public String getHandledPropertyName() {
        return CliOptions.CURRENT_ARTIFACT_VERSION;
    }

    @Override
    public String getHelpMessage() {
        return "Specifies the current artifact version against the latest artifact resolution. Example: 1.0.0, 1.0.0-SNAPSHOT";
    }
}
