package com.arnoldgalovics.blog.swagger.breaker.cli.options.handler;

import com.arnoldgalovics.blog.swagger.breaker.runner.Options;

public interface CliOptionHandler {
    void handle(String propertyValue, Options options);

    String getHandledPropertyName();
}
