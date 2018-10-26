package com.arnoldgalovics.blog.swagger.breaker.core.rule.response;

import static java.lang.String.format;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class ResponseTypeAttributeRemovedBreakingChange implements BreakingChange {
    private final String schemaName;
    private final String attributeName;

    @Override
    public String getMessage() {
        return format("%s was removed from %s schema", attributeName, schemaName);
    }
}
