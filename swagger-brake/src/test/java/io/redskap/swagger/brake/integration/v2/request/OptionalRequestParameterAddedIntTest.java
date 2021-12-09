package io.redskap.swagger.brake.integration.v2.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
public class OptionalRequestParameterAddedIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testOptionalRequestParameterAddedWorksCorrectly() {
        // given
        String oldApiPath = "swaggers/v2/request/optionalparameteradded/swagger_v1.yaml";
        String newApiPath = "swaggers/v2/request/optionalparameteradded/swagger_v2.yaml";
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).isEmpty();
    }
}