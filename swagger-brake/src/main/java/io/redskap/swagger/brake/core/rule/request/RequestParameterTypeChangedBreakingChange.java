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
public class RequestParameterTypeChangedBreakingChange implements BreakingChange {
    private final String path;
    private final HttpMethod method;
    private final String name;
    private final String attributeName;
    private final String oldType;
    private final String newType;

    @Override
    public String getMessage() {
        return format("%s parameter type has been changed in %s %s at attribute %s from %s to %s", name, method, path, attributeName, oldType, newType);
    }

    @Override
    public String getRuleCode() {
        return "R008";
    }
}