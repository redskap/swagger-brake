package io.redskap.swagger.brake.cli.options.handler;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.runner.Options;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class VerboseHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (propertyValue == null) {
            return;
        }

        if (propertyValue.isEmpty()) {
            org.slf4j.Logger slf4jLogger = LoggerFactory.getLogger("io.redskap.swagger.brake");
            if (slf4jLogger instanceof Logger) {
                Logger logger = (Logger) slf4jLogger;
                logger.setLevel(Level.DEBUG);
                logger.detachAppender("INFO_STDOUT");
                logger.addAppender(getVerboseAppender());
            }
        }
    }

    private Appender<ILoggingEvent> getVerboseAppender() {
        Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        return rootLogger.getAppender("VERBOSE_STDOUT");
    }

    @Override
    public String getHandledPropertyName() {
        return CliOptions.VERBOSE;
    }

    @Override
    public String getHelpMessage() {
        return "Sets verbose logging";
    }
}
