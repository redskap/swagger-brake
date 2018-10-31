package io.redskap.swagger.brake.integration.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Collections;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.HttpMethod;
import io.redskap.swagger.brake.core.rule.request.RequestParameterInTypeChangedBreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RequestParameterInTypeChangedIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testRequestParameterInTypeChangedWorksCorrectly() {
        // given
        String oldApiPath = "request/parameterintypechanged/petstore.yaml";
        String newApiPath = "request/parameterintypechanged/petstore_v2.yaml";
        RequestParameterInTypeChangedBreakingChange bc = new RequestParameterInTypeChangedBreakingChange("/pet/findByStatus", HttpMethod.GET, "status", "query", "header");
        Collection<BreakingChange> expected = Collections.singleton(bc);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }
}
