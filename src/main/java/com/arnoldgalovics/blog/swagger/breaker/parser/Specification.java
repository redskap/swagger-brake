package com.arnoldgalovics.blog.swagger.breaker.parser;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class Specification {
    private final Collection<Path> paths;
}
