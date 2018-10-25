package com.arnoldgalovics.blog.swagger.breaker.core.rule.path;

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
public class PathDeletedBreakingChange implements BreakingChange {
    private final String path;
    private final HttpMethod method;

    @Override
    public String getMessage() {
        return format("Path %s %s has been deleted", path, method);
    }
}
