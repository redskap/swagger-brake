package io.redskap.swagger.brake.report.html;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.redskap.swagger.brake.core.BreakingChange;
import lombok.Data;

@Data
public class BreakingChangeTableRow {
    @JsonUnwrapped
    private BreakingChange breakingChange;
    private String message;

    public BreakingChangeTableRow(BreakingChange breakingChange) {
        this.breakingChange = breakingChange;
        this.message = breakingChange.getMessage();
    }
}
