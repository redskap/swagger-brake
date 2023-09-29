package io.redskap.swagger.brake.report;

import static java.util.stream.Collectors.groupingBy;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import io.redskap.swagger.brake.core.ApiInfo;
import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.report.file.DirectoryCreator;
import io.redskap.swagger.brake.report.file.FileWriter;
import io.redskap.swagger.brake.report.json.JsonConverter;
import io.redskap.swagger.brake.runner.OutputFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class JsonReporter extends AbstractFileReporter implements CheckableReporter {
    private static final String DEFAULT_FILENAME = "swagger-brake.json";
    private static final String VERSIONED_FILENAME_TEMPLATE = "swagger-brake-%s.json";
    private final JsonConverter jsonConverter;

    public JsonReporter(FileWriter fileWriter, DirectoryCreator directoryCreator, JsonConverter jsonConverter) {
        super(fileWriter, directoryCreator);
        this.jsonConverter = jsonConverter;
    }

    @Override
    protected String getFilename(ApiInfo apiInfo) {
        String result = DEFAULT_FILENAME;
        if (apiInfo != null && isNotBlank(apiInfo.getVersion())) {
            result = VERSIONED_FILENAME_TEMPLATE.formatted(apiInfo.getVersion());
        }
        return result;
    }

    @Override
    protected String toFileContent(Collection<BreakingChange> breakingChanges, ApiInfo apiInfo) {
        Map<String, List<BreakingChange>> nameMapping = breakingChanges.stream().collect(groupingBy(BreakingChange::getRuleCode));
        return jsonConverter.convert(new JsonContent(apiInfo, nameMapping));
    }

    @Override
    public boolean canReport(OutputFormat format) {
        return OutputFormat.JSON.equals(format);
    }

    @RequiredArgsConstructor
    @Getter
    static class JsonContent {
        private final ApiInfo info;
        private final Map<String, List<BreakingChange>> breakingChanges;
    }
}
