package io.redskap.swagger.brake.report;

import static java.util.stream.Collectors.groupingBy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.report.file.FileWriter;
import io.redskap.swagger.brake.report.json.JsonConverter;
import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.OutputFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonReporter implements Reporter {
    private static final String FILENAME = "swagger-brake.json";
    private final FileWriter fileWriter;
    private final JsonConverter jsonConverter;

    @Override
    public void report(Collection<BreakingChange> breakingChanges, Options options) {
        Map<String, List<BreakingChange>> nameMapping = breakingChanges.stream().collect(groupingBy(this::getBreakingChangeName));
        String json = jsonConverter.convert(nameMapping);
        try {
            Files.createDirectories(Paths.get(options.getOutputFilePath()));
            String filePath = options.getOutputFilePath() + File.separator + FILENAME;
            fileWriter.write(filePath, json);
        } catch (IOException e) {
            throw new RuntimeException("An exception occurred while writing the report file", e);
        }
    }

    @Override
    public boolean canReport(OutputFormat format) {
        return OutputFormat.JSON.equals(format);
    }

    private String getBreakingChangeName(BreakingChange e) {
        String clazzName = e.getClass().getSimpleName();
        return clazzName.substring(0, 1).toLowerCase() + clazzName.substring(1);
    }
}
