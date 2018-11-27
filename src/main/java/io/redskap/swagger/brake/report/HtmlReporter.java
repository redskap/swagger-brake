package io.redskap.swagger.brake.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;
import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.report.file.DirectoryCreator;
import io.redskap.swagger.brake.report.file.FileWriter;
import io.redskap.swagger.brake.report.html.BreakingChangeTableRow;
import io.redskap.swagger.brake.report.html.HtmlData;
import io.redskap.swagger.brake.report.html.MustacheContentResolver;
import io.redskap.swagger.brake.report.json.JsonConverter;
import io.redskap.swagger.brake.runner.OutputFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HtmlReporter extends AbstractFileReporter {
    private static final String FILENAME = "swagger-brake.html";
    private final JsonConverter jsonConverter;
    private final MustacheContentResolver mustacheContentResolver;

    public HtmlReporter(FileWriter fileWriter, DirectoryCreator directoryCreator, JsonConverter jsonConverter, MustacheContentResolver mustacheContentResolver) {
        super(fileWriter, directoryCreator);
        this.jsonConverter = jsonConverter;
        this.mustacheContentResolver = mustacheContentResolver;
    }

    @Override
    protected String getFilename() {
        return FILENAME;
    }

    @Override
    protected String toFileContent(Collection<BreakingChange> breakingChanges) {
        String mustacheTemplate = "htmlreporter/swagger-brake.mustache";
        HtmlData data = new HtmlData();
        List<BreakingChangeTableRow> tableRows = breakingChanges.stream().map(BreakingChangeTableRow::new).collect(Collectors.toCollection(ArrayList::new));
        if (!tableRows.isEmpty()) {
            data.setBreakingChanges(tableRows);
        }
        Map<String, Map<String, Object>> paramMap = ImmutableMap.of("data", jsonConverter.toMap(data));
        return mustacheContentResolver.resolve(mustacheTemplate, paramMap);
    }

    @Override
    public boolean canReport(OutputFormat format) {
        return OutputFormat.HTML.equals(format);
    }
}
