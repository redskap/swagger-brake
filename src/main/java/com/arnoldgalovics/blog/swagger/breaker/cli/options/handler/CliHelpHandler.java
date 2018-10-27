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
        sb.append(format("Usage: java -jar swagger-breaker.jar %s=/home/user/something.yml %s=/home/user/something_v2.yml",
            formatCliOption(CliOptions.OLD_API_PATH), formatCliOption(CliOptions.NEW_API_PATH)));
        sb.append(lineSeparator());
        sb.append(lineSeparator());
        sb.append("Parameters:");
        sb.append(lineSeparator());
        sb.append(format("\t%s The absolute path of the old api file", formatPadCliOption(CliOptions.OLD_API_PATH)));
        sb.append(lineSeparator());
        sb.append(format("\t%s The absolute path of the new api file", formatPadCliOption(CliOptions.NEW_API_PATH)));
        sb.append(lineSeparator());
        return sb.toString();
    }

    private String formatPadCliOption(String oldApiPath) {
        return StringUtils.rightPad(formatCliOption(oldApiPath), 15);
    }

    private String formatCliOption(String oldApiPath) {
        return CliOptions.getAsCliOption(oldApiPath);
    }
}
