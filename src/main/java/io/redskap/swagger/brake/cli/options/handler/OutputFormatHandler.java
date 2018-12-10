package io.redskap.swagger.brake.cli.options.handler;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.OutputFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OutputFormatHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        Set<OutputFormat> formats = ImmutableSet.of(OutputFormat.STDOUT);

        if (!StringUtils.isBlank(propertyValue)) {
            try {
                String[] formatStrings = propertyValue.split(",");
                formats = Arrays.stream(formatStrings).map(String::trim).map(String::toUpperCase).map(OutputFormat::valueOf).collect(toSet());
            } catch (IllegalArgumentException e) {
                log.debug("Format cannot be resolved", e);
            }
        }

        options.setOutputFormats(ImmutableSet.copyOf(formats));
    }

    @Override
    public String getHandledPropertyName() {
        return CliOptions.OUTPUT_FORMAT;
    }

    @Override
    public String getHelpMessage() {
        List<String> values = Arrays.stream(OutputFormat.values()).map(Enum::name).map(String::toLowerCase).collect(toList());
        return format("Specifies the output format. Possible values are: %s", StringUtils.join(values, ", "));
    }
}
