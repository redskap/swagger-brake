package io.redskap.swagger.brake.cli.options.handler;

import io.redskap.swagger.brake.cli.options.CliOption;
import io.redskap.swagger.brake.runner.Options;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
class OldApiPathHandler implements CliOptionHandler {
    @Override
    public void handle(String optionValue, Options options) {
        if (StringUtils.isNotBlank(optionValue)) {
            options.setOldApiPath(optionValue);
        }
    }

    @Override
    public CliOption getHandledCliOption() {
        return CliOption.OLD_API_PATH;
    }

    @Override
    public String getHelpMessage() {
        return "The absolute path of the old api file";
    }
}
