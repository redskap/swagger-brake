package com.arnoldgalovics.blog.swagger.breaker.runner;

import lombok.Data;

@Data
public class Options {
    private String oldApiPath;
    private String newApiPath;
}
