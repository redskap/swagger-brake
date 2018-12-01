package io.redskap.swagger.brake.cli.options.handler;

import static org.assertj.core.api.Assertions.assertThat;

import io.redskap.swagger.brake.runner.Options;
import org.junit.Test;

public class NewApiPathHandlerTest {
    private NewApiPathHandler underTest = new NewApiPathHandler();

    @Test(expected = IllegalArgumentException.class)
    public void testHandleShouldThrowExceptionWhenPropertyValueIsNull() {
        // given
        Options options = new Options();
        // when
        underTest.handle(null, options);
        // then exception thrown
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHandleShouldThrowExceptionWhenPropertyValueIsEmpty() {
        // given
        Options options = new Options();
        // when
        underTest.handle("", options);
        // then exception thrown
    }

    @Test
    public void testHandleShouldSetPropertyValueWhenNotEmpty() {
        // given
        Options options = new Options();
        String propertyValue = "something";
        // when
        underTest.handle(propertyValue, options);
        // then
        assertThat(options.getNewApiPath()).isEqualTo(propertyValue);
    }
}