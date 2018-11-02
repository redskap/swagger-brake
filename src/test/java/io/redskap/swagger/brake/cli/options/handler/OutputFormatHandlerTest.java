package io.redskap.swagger.brake.cli.options.handler;

import static org.assertj.core.api.Assertions.assertThat;

import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.OutputFormat;
import org.junit.Test;

public class OutputFormatHandlerTest {
    private OutputFormatHandler underTest = new OutputFormatHandler();

    @Test
    public void testHandleShouldSetFormatToStandardOutWhenPropertyValueIsNull() {
        // given
        Options options = new Options();
        // when
        underTest.handle(null, options);
        // then
        assertThat(options.getOutputFormat()).isEqualTo(OutputFormat.STDOUT);
    }

    @Test
    public void testHandleShouldSetFormatToStandardOutWhenPropertyValueIsEmpty() {
        // given
        Options options = new Options();
        // when
        underTest.handle("", options);
        // then
        assertThat(options.getOutputFormat()).isEqualTo(OutputFormat.STDOUT);
    }

    @Test
    public void testHandleShouldSetFormatToStandardOutWhenPropertyValueIsNotMappable() {
        // given
        Options options = new Options();
        // when
        underTest.handle("something", options);
        // then
        assertThat(options.getOutputFormat()).isEqualTo(OutputFormat.STDOUT);
    }

    @Test
    public void testHandleShouldSetFormatToStandardOutWhenPropertyValueIsStdOut() {
        // given
        Options options = new Options();
        // when
        underTest.handle("stdout", options);
        // then
        assertThat(options.getOutputFormat()).isEqualTo(OutputFormat.STDOUT);
    }

    @Test
    public void testHandleShouldSetFormatToStandardOutWhenPropertyValueIsJson() {
        // given
        Options options = new Options();
        // when
        underTest.handle("json", options);
        // then
        assertThat(options.getOutputFormat()).isEqualTo(OutputFormat.JSON);
    }
}