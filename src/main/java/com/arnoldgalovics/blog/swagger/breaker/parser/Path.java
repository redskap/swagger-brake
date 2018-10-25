package com.arnoldgalovics.blog.swagger.breaker.parser;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Path {
    private String path;
    private String method;
}
