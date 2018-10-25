package com.arnoldgalovics.blog.swagger.breaker.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Path {
    private String path;
    private HttpMethod method;
}
