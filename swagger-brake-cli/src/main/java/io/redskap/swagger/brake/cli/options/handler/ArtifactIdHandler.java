package io.redskap.swagger.brake.cli.options.handler;

import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.runner.Options;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ArtifactIdHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (StringUtils.isNotBlank(propertyValue)) {
            options.setArtifactId(propertyValue);
        }
    }

    @Override
    public String getHandledPropertyName() {
        return CliOptions.ARTIFACT_ID;
    }

    @Override
    public String getHelpMessage() {
        return "Specifies the artifact id which will be used for latest version resolution";
    }
}
