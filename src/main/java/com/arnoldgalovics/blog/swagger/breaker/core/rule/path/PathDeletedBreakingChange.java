package com.arnoldgalovics.blog.swagger.breaker.core.rule.path;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import static java.lang.String.format;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class PathDeletedBreakingChange implements BreakingChange {
    private final String path;
    private final String method;

    @Override
    public String getMessage() {
        return format("Path %s %s has been deleted", path, method);
    }
}
