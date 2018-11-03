package io.redskap.swagger.brake.cli.options.handler;

import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.runner.Options;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class NexusRepoUrlHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (StringUtils.isNotBlank(propertyValue)) {
            options.setNexusRepoUrl(propertyValue);
        }
    }

    @Override
    public String getHandledPropertyName() {
        return CliOptions.NEXUS_REPO_URL;
    }

    @Override
    public String getHelpMessage() {
        return "Specifies the Nexus snapshot repository URL where the latest artifact denoted by the groupId and artifactId will be downloaded";
    }
}
