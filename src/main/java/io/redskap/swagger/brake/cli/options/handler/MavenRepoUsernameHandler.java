package io.redskap.swagger.brake.cli.options.handler;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import io.redskap.swagger.brake.runner.Options;
import org.springframework.stereotype.Component;

@Component
public class MavenRepoUsernameHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (isNotBlank(propertyValue)) {
            options.setMavenRepoUsername(propertyValue);
        }
    }

    @Override
    public String getHandledPropertyName() {
        return "maven-repo-username";
    }

    @Override
    public String getHelpMessage() {
        return "Specifies the username to access the Maven repository";
    }
}
