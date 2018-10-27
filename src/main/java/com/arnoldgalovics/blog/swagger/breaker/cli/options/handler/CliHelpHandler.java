package com.arnoldgalovics.blog.swagger.breaker.cli.options.handler;

import static java.lang.String.format;
import static java.lang.System.lineSeparator;

import com.arnoldgalovics.blog.swagger.breaker.cli.options.CliOptions;
import com.arnoldgalovics.blog.swagger.breaker.runner.Options;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CliHelpHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (propertyValue == null) {
            return;
        }

        if (propertyValue.isEmpty()) {
            throw new CliHelpException(getHelp());
        }
    }

    @Override
    public String getHandledPropertyName() {
        return CliOptions.HELP;
    }

    private String getHelp() {
        StringBuilder sb = new StringBuilder();
        sb.append(format("Usage: java -jar swagger-breaker.jar "));
        sb.append(lineSeparator());
        sb.append(format("%s The absolute path of the old api file", formatCliOption(CliOptions.OLD_API_PATH)));
        sb.append(lineSeparator());
        sb.append(format("%s The absolute path of the new api file", formatCliOption(CliOptions.NEW_API_PATH)));
        sb.append(lineSeparator());
        return sb.toString();
    }

    private String formatCliOption(String oldApiPath) {
        return StringUtils.rightPad(CliOptions.getAsCliOption(oldApiPath), 10);
    }
}
