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
    private final CliHelpProvider helpProvider;
    private final Environment environment;

    public Options provide() {
        if (isHelpRequired()) {
            throw new CliHelpException(helpProvider.getHelp());
        }
        Options options = new Options();
        for (CliOptionHandler handler : handlers) {
            String property = environment.getProperty(handler.getHandledPropertyName());
            handler.handle(property, options);
        }
        return options;
    }

    private boolean isHelpRequired() {
        return environment.getProperty(CliOptions.HELP) != null;
    }
}
