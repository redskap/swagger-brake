package io.redskap.swagger.brake.integration.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.HttpMethod;
import io.redskap.swagger.brake.core.rule.request.RequestParameterRequiredBreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RequestParameterRequiredIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testRequestParameterRequiredWorksCorrectly() {
        // given
        String oldApiPath = "request/parameterrequired/petstore.yaml";
        String newApiPath = "request/parameterrequired/petstore_v2.yaml";
        RequestParameterRequiredBreakingChange bc1 = new RequestParameterRequiredBreakingChange("/pet", HttpMethod.POST, "test");
        RequestParameterRequiredBreakingChange bc2 = new RequestParameterRequiredBreakingChange("/pet/findByStatus", HttpMethod.GET, "test");
        Collection<BreakingChange> expected = Arrays.asList(bc1, bc2);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(2);
        assertThat(result).hasSameElementsAs(expected);
    }
}
