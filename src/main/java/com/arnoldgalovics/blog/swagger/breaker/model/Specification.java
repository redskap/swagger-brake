package com.arnoldgalovics.blog.swagger.breaker.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class Specification {
    private final Collection<Path> paths;
}
