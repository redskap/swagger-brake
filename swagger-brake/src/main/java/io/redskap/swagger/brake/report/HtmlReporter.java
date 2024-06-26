package io.redskap.swagger.brake.report;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import io.redskap.swagger.brake.core.ApiInfo;
import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.report.file.DirectoryCreator;
import io.redskap.swagger.brake.report.file.FileWriter;
import io.redskap.swagger.brake.report.html.BreakingChangeTableRow;
import io.redskap.swagger.brake.report.html.HtmlData;
import io.redskap.swagger.brake.report.html.MustacheContentResolver;
import io.redskap.swagger.brake.report.json.JsonConverter;
import io.redskap.swagger.brake.runner.OutputFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class HtmlReporter extends AbstractFileReporter implements CheckableReporter {
    private static final String DEFAULT_FILENAME = "swagger-brake.html";
    private static final String VERSIONED_FILENAME_TEMPLATE = "swagger-brake-%s.html";
    private final JsonConverter jsonConverter;
    private final MustacheContentResolver mustacheContentResolver;

    public HtmlReporter(FileWriter fileWriter, DirectoryCreator directoryCreator, JsonConverter jsonConverter, MustacheContentResolver mustacheContentResolver) {
        super(fileWriter, directoryCreator);
        this.jsonConverter = jsonConverter;
        this.mustacheContentResolver = mustacheContentResolver;
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
    protected String toFileContent(Collection<BreakingChange> breakingChanges, Collection<BreakingChange> ignoredBreakingChanges, ApiInfo apiInfo) {
        String mustacheTemplate = "htmlreporter/swagger-brake.mustache";
        HtmlData data = new HtmlData();
        List<BreakingChangeTableRow> tableRows = breakingChanges.stream().map(BreakingChangeTableRow::new).collect(Collectors.toCollection(ArrayList::new));
        if (!tableRows.isEmpty()) {
            data.setBreakingChanges(tableRows);
        }
        List<BreakingChangeTableRow> ignoredTableRows = ignoredBreakingChanges.stream().map(BreakingChangeTableRow::new).collect(Collectors.toCollection(ArrayList::new));
        if (!ignoredBreakingChanges.isEmpty()) {
            data.setIgnoredBreakingChanges(ignoredTableRows);
        }

        Map<String, Map<String, Object>> paramMap = new HashMap<>();
        paramMap.put("data", jsonConverter.toMap(data));
        if (apiInfo != null) {
            paramMap.put("info", jsonConverter.toMap(apiInfo));
        }
        return mustacheContentResolver.resolve(mustacheTemplate, paramMap);
    }

    @Override
    public boolean canReport(OutputFormat format) {
        return OutputFormat.HTML.equals(format);
    }
}
