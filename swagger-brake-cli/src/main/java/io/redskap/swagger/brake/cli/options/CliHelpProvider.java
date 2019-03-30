package io.redskap.swagger.brake.cli.options;

import static java.lang.String.format;
import static java.lang.System.lineSeparator;

import java.util.Collection;

import io.redskap.swagger.brake.cli.options.handler.CliOptionHandler;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CliHelpProvider {
    private final Collection<CliOptionHandler> optionHandlers;

    public String getHelp() {
        StringBuilder sb = new StringBuilder();
        sb.append(format("Usage: java -jar swagger-brake.jar %s=/home/user/something.yaml %s=/home/user/something_v2.yaml",
            formatCliOption(CliOptions.OLD_API_PATH), formatCliOption(CliOptions.NEW_API_PATH)));
        sb.append(lineSeparator());
        sb.append(lineSeparator());
        sb.append("Parameters:");
        for (CliOptionHandler handler : optionHandlers) {
            sb.append(lineSeparator());
            sb.append("\t");
            sb.append(format("%s %s", formatPadCliOption(handler.getHandledPropertyName()), handler.getHelpMessage()));
        }
        return sb.toString();
    }

    private String formatPadCliOption(String option) {
        return StringUtils.rightPad(formatCliOption(option), 40);
    }

    private String formatCliOption(String option) {
        return CliOptions.getAsCliOption(option);
    }
}
