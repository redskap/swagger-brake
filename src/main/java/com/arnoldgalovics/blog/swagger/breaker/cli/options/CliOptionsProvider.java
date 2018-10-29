package com.arnoldgalovics.blog.swagger.breaker.cli.options;

import java.util.List;

import com.arnoldgalovics.blog.swagger.breaker.cli.options.handler.CliOptionHandler;
import com.arnoldgalovics.blog.swagger.breaker.runner.Options;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CliOptionsProvider {
    private final List<CliOptionHandler> handlers;
    private final CliHelpProvider helpProvider;
    private final Environment environment;

    public Options provide() {
        if (isHelpRequired()) {
            throw new CliHelpException(helpProvider.getHelp());
        }
        Options options = new Options();
        for (CliOptionHandler handler : handlers) {
            String handledPropertyName = handler.getHandledPropertyName();
            String propertyValue = environment.getProperty(handledPropertyName);
            log.debug("Handling command line argument {} with value of {}", handledPropertyName, propertyValue);
            handler.handle(propertyValue, options);
        }
        return options;
    }

    private boolean isHelpRequired() {
        return environment.getProperty(CliOptions.HELP) != null;
    }
}
