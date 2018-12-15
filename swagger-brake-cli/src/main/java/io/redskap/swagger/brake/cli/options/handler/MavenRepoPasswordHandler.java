package io.redskap.swagger.brake.cli.options.handler;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import io.redskap.swagger.brake.runner.Options;
import org.springframework.stereotype.Component;

@Component
public class MavenRepoPasswordHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (isNotBlank(propertyValue)) {
            options.setMavenRepoPassword(propertyValue);
        }
    }

    @Override
    public String getHandledPropertyName() {
        return "maven-repo-password";
    }

    @Override
    public String getHelpMessage() {
        return "Specifies the password to access the Maven repository";
    }
}
