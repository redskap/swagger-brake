package io.redskap.swagger.brake.integration.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RequestParameterRefResolutionIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testRequestParameterRefResolutionWorks() {
        // given
        String apiPath = "request/refresolution/petstore.yaml";

        // when
        Collection<BreakingChange> result = execute(apiPath, apiPath);
        // then
        assertThat(result).isEmpty();
    }
}