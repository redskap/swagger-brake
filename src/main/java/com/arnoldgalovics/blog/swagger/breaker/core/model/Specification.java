package com.arnoldgalovics.blog.swagger.breaker.core.model;

import java.util.Collection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Specification {
    private final Collection<Path> paths;
}
