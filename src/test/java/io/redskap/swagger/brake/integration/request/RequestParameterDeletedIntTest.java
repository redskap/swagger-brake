package io.redskap.swagger.brake.integration.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Collections;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.HttpMethod;
import io.redskap.swagger.brake.core.rule.request.RequestParameterDeletedBreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RequestParameterDeletedIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testRequestParameterDeletedWorksCorrectly() {
        // given
        String oldApiPath = "request/parameterdeleted/petstore.yaml";
        String newApiPath = "request/parameterdeleted/petstore_v2.yaml";
        RequestParameterDeletedBreakingChange bc = new RequestParameterDeletedBreakingChange("/pet/findByStatus", HttpMethod.GET, "status");
        Collection<BreakingChange> expected = Collections.singleton(bc);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }
}
