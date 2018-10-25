package com.arnoldgalovics.blog.swagger.breaker.core.rule.path;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

@Getter
@RequiredArgsConstructor
public class PathDeletedBreakingChange implements BreakingChange {
    private final String path;
    private final String method;

    @Override
    public String getMessage() {
        return format("Path %s %s has been deleted", path, method);
    }
}
