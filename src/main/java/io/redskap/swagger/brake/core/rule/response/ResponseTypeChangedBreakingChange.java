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
public class ResponseTypeChangedBreakingChange implements BreakingChange {
    private final String path;
    private final HttpMethod method;
    private final String code;
    private final String attribute;
    private final String oldType;
    private final String newType;

    @Override
    public String getMessage() {
        return format("Response type was changed for response %s in %s %s at attribute %s from %s to %s", code, method, path, attribute, oldType, newType);
    }
}
