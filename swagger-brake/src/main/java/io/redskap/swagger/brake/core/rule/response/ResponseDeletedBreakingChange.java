package io.redskap.swagger.brake.core.rule.response;

import static java.lang.String.format;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.HttpMethod;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class ResponseDeletedBreakingChange implements BreakingChange {
    private final String path;
    private final HttpMethod method;
    private final String code;

    @Override
    public String getMessage() {
        return format("Response %s has been removed from %s %s", code, method, path);
    }

    @Override
    public String getRuleCode() {
        return "R012";
    }
}
