package com.arnoldgalovics.blog.swagger.breaker.core.model;

import java.util.Arrays;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RequestParameterInType {
    QUERY("query"),
    PATH("path"),
    HEADER("header"),
    COOKIE("cookie");

    private final String name;

    public String getName() {
        return name;
    }

    public static RequestParameterInType fromName(String name) {
        return Arrays.stream(RequestParameterInType.values())
            .filter(t -> name.equals(t.getName()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("Name " + name + " cannot be resolved"));
    }
}
