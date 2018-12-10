package io.redskap.swagger.brake.report;

import io.redskap.swagger.brake.runner.OutputFormat;

public interface CheckableReporter extends Reporter {
    boolean canReport(OutputFormat format);
}
