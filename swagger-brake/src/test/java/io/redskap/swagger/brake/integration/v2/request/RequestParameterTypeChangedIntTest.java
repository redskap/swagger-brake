package io.redskap.swagger.brake.integration.v2.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Collections;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.HttpMethod;
import io.redskap.swagger.brake.core.rule.request.RequestParameterTypeChangedBreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RequestParameterTypeChangedIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testRequestParameterTypeChangedWorksCorrectly() {
        // given
        String oldApiPath = "swaggers/v2/request/parametertypechanged/petstore.yaml";
        String newApiPath = "swaggers/v2/request/parametertypechanged/petstore_v2.yaml";
        RequestParameterTypeChangedBreakingChange bc = new RequestParameterTypeChangedBreakingChange("/pet/findByTags", HttpMethod.GET, "tags", "", "array", "string");
        Collection<BreakingChange> expected = Collections.singleton(bc);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }
}
