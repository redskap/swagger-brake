package com.arnoldgalovics.blog.swagger.breaker.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class SchemaAttribute {
    private final String name;
}
