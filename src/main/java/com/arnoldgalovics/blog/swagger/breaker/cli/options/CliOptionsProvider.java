package com.arnoldgalovics.blog.swagger.breaker.cli.options;

import java.util.Collection;

import com.arnoldgalovics.blog.swagger.breaker.cli.options.handler.CliOptionHandler;
import com.arnoldgalovics.blog.swagger.breaker.runner.Options;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CliOptionsProvider {
    private final Collection<CliOptionHandler> handlers;
    private final Environment environment;

    public Options provide() {
        Options options = new Options();
        for (CliOptionHandler handler : handlers) {
            String property = environment.getProperty(handler.getHandledPropertyName());
            handler.handle(property, options);
        }
        return options;
    }
}
