package io.redskap.swagger.brake.cli.options.handler;

import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.runner.Options;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class BetaApiExtensionNameHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (StringUtils.isNotBlank(propertyValue)) {
            options.setBetaApiExtensionName(propertyValue);
        }
    }

    @Override
    public String getHandledPropertyName() {
        return CliOptions.BETA_API_EXTENSION_NAME;
    }

    @Override
    public String getHelpMessage() {
        return "Specifies the beta API extension attribute name. Defaults to 'x-beta-api'.";
    }
}
