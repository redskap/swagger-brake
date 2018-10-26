package com.arnoldgalovics.blog.swagger.breaker.core.rule.request;

import static java.lang.String.format;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.model.HttpMethod;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class RequestParameterEnumValueDeletedBreakingChange implements BreakingChange {
    private final String path;
    private final HttpMethod method;
    private final String name;
    private final String enumValue;

    @Override
    public String getMessage() {
        return format("Enum value %s has been deleted for parameter %s in %s %s", enumValue, name, method, path);
    }
}
