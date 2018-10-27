package com.arnoldgalovics.blog.swagger.breaker.core.rule.response;

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
public class ResponseTypeChangedBreakingChange implements BreakingChange {
    private final String path;
    private final HttpMethod method;
    private final String code;
    private final String attribute;
    private final String oldType;
    private final String newType;

    @Override
    public String getMessage() {
        return format("Response type was change for response %s in %s %s at attribute %s from %s to %s", code, method, path, attribute, oldType, newType);
    }
}
