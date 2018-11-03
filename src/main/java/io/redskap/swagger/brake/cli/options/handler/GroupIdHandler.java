package io.redskap.swagger.brake.cli.options.handler;

import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.runner.Options;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class GroupIdHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (StringUtils.isNotBlank(propertyValue)) {
            options.setGroupId(propertyValue);
        }
    }

    @Override
    public String getHandledPropertyName() {
        return CliOptions.GROUP_ID;
    }

    @Override
    public String getHelpMessage() {
        return "Specifies the group id which will be used for latest version resolution";
    }
}
