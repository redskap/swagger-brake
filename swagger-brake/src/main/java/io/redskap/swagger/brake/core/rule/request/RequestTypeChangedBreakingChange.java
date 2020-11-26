package io.redskap.swagger.brake.core.rule.request;

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
public class RequestTypeChangedBreakingChange implements BreakingChange {
    private final String path;
    private final HttpMethod method;
    private final String attributeName;
    private final String fromType;
    private final String toType;

    @Override
    public String getMessage() {
        return format("%s type was changed in %s %s from %s to %s ", attributeName, method, path, fromType, toType);
    }

    @Override
    public String getRuleCode() {
        return "R010";
    }
}
