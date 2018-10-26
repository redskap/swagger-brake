package com.arnoldgalovics.blog.swagger.breaker.core.model;

import java.util.Arrays;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RequestParameterType {
    QUERY("query"),
    PATH("path"),
    HEADER("header"),
    COOKIE("cookie");

    private final String name;

    public String getName() {
        return name;
    }

    public static RequestParameterType fromName(String name) {
        return Arrays.stream(RequestParameterType.values())
            .filter(t -> name.equals(t.getName()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("Name " + name + " cannot be resolved"));
    }
}
