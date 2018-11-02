package io.redskap.swagger.brake.report;

import static java.util.stream.Collectors.groupingBy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.redskap.swagger.brake.core.BreakingChange;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonReporter implements Reporter {
    private static final String FILENAME = "swagger-brake.json";
    private final String outputFilePath;
    private final ObjectMapper objectMapper;

    public JsonReporter(String outputFilePath) {
        this.outputFilePath = outputFilePath;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public void report(Collection<BreakingChange> breakingChanges) {
        Map<String, List<BreakingChange>> nameMapping = breakingChanges.stream().collect(groupingBy(this::getBreakingChangeName));
        String json = convertToJson(nameMapping);
        try {
            String filePath = outputFilePath + File.separator + FILENAME;
            log.debug("File path {}", filePath);
            Path path = Paths.get(filePath);
            Files.write(path, Collections.singleton(json));
        } catch (IOException e) {
            throw new RuntimeException("Error happened while writing the output file", e);
        }
    }

    private String convertToJson(Map<String, List<BreakingChange>> nameMapping) {
        try {
            return objectMapper.writeValueAsString(nameMapping);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getBreakingChangeName(BreakingChange e) {
        String clazzName = e.getClass().getSimpleName();
        return clazzName.substring(0, 1).toLowerCase() + clazzName.substring(1);
    }
}
