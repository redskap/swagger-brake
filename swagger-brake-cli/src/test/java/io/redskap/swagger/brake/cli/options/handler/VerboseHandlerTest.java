package io.redskap.swagger.brake.cli.options.handler;

import static org.assertj.core.api.Assertions.assertThat;

import io.redskap.swagger.brake.cli.options.CliOption;
import io.redskap.swagger.brake.runner.Options;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerboseHandlerTest {
    private VerboseHandler underTest = new VerboseHandler();

    @Test
    public void testHandleWorks() {
        // given
        String propertyValue = "";
        Options options = new Options();
        // when
        underTest.handle(propertyValue, options);
        // then
        assertThat(options).isEqualTo(new Options());
        Logger logger = LoggerFactory.getLogger("io.redskap.swagger.brake");
        assertThat(logger.isDebugEnabled()).isTrue();
    }

    @Test
    public void testHandleDoesNotDoAnythingIfPropertyIsNull() {
        // given
        String propertyValue = null;
        Options options = new Options();
        // when
        underTest.handle(propertyValue, options);
        // then
        assertThat(options).extracting(Options::getOutputFilePath).isNull();
    }

    @Test
    public void testGetHandledCliOptionIsCorrect() {
        // given
        // when
        CliOption result = underTest.getHandledCliOption();
        // then
        assertThat(result).isEqualTo(CliOption.VERBOSE);
    }
}