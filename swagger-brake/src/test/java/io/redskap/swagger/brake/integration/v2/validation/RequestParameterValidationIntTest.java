package io.redskap.swagger.brake.integration.v2.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RequestParameterValidationIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testValidationErrorHappensWhenHeaderUsesSchema() {
        // given
        String oldApiPath = "swaggers/v2/validation/requestparam/header_swagger.json";
        String newApiPath = "swaggers/v2/validation/requestparam/header_swagger.json";
        // when
        Throwable exception = catchThrowable(() -> execute(oldApiPath, newApiPath));
        // then
        assertThat(exception)
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("schema does not have any type");
    }

    @Test
    public void testValidationErrorHappensWhenQueryUsesSchema() {
        // given
        String oldApiPath = "swaggers/v2/validation/requestparam/query_swagger.json";
        String newApiPath = "swaggers/v2/validation/requestparam/query_swagger.json";
        // when
        Throwable exception = catchThrowable(() -> execute(oldApiPath, newApiPath));
        // then
        assertThat(exception)
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("schema does not have any type");
    }

    @Test
    public void testValidationErrorHappensWhenPathUsesSchema() {
        // given
        String oldApiPath = "swaggers/v2/validation/requestparam/path_swagger.json";
        String newApiPath = "swaggers/v2/validation/requestparam/path_swagger.json";
        // when
        Throwable exception = catchThrowable(() -> execute(oldApiPath, newApiPath));
        // then
        assertThat(exception)
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("schema does not have any type");
    }
}
