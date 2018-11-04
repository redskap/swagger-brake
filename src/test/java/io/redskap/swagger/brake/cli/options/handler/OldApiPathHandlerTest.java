package io.redskap.swagger.brake.cli.options.handler;

import static org.assertj.core.api.Assertions.assertThat;

import io.redskap.swagger.brake.runner.Options;
import org.junit.Test;

public class OldApiPathHandlerTest {
    private OldApiPathHandler underTest = new OldApiPathHandler();

    @Test
    public void testHandleShouldSetPropertyValueWhenNotEmpty() {
        // given
        Options options = new Options();
        String propertyValue = "something";
        // when
        underTest.handle(propertyValue, options);
        // then
        assertThat(options.getOldApiPath()).isEqualTo(propertyValue);
    }
}