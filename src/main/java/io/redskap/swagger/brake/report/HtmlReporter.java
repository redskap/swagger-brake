package io.redskap.swagger.brake.report;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.common.collect.ImmutableMap;
import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.report.file.FileWriter;
import io.redskap.swagger.brake.report.html.BreakingChangeTableRow;
import io.redskap.swagger.brake.report.html.HtmlData;
import io.redskap.swagger.brake.report.json.JsonConverter;
import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.OutputFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HtmlReporter implements Reporter {
    private static final String FILENAME = "swagger-brake.html";
    private final JsonConverter jsonConverter;
    private final FileWriter fileWriter;

    @Override
    public void report(Collection<BreakingChange> breakingChanges, Options options) {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("htmlreporter/swagger-brake.mustache");
        StringWriter sw = new StringWriter();
        try {
            HtmlData data = new HtmlData();
            List<BreakingChangeTableRow> tableRows = breakingChanges.stream().map(BreakingChangeTableRow::new).collect(Collectors.toCollection(ArrayList::new));
            if (!tableRows.isEmpty()) {
                data.setBreakingChanges(tableRows);
            }
            m.execute(sw, ImmutableMap.of("data", jsonConverter.toMap(data))).flush();
            String filePath = options.getOutputFilePath() + File.separator + FILENAME;
            fileWriter.write(filePath, sw.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean canReport(OutputFormat format) {
        return OutputFormat.HTML.equals(format);
    }
}
