package io.redskap.swagger.brake.integration.v2.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.HttpMethod;
import io.redskap.swagger.brake.core.rule.request.RequestTypeChangedBreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RequestTypeChangedIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testRequestTypeChangeIsBreakingChange() {
        // given
        String oldApiPath = "swaggers/v2/request/attributetypechanged/petstore.yaml";
        String newApiPath = "swaggers/v2/request/attributetypechanged/petstore_v2.yaml";
        RequestTypeChangedBreakingChange bc1 = new RequestTypeChangedBreakingChange("/pet", HttpMethod.PUT, "id", "integer", "string");
        RequestTypeChangedBreakingChange bc2 = new RequestTypeChangedBreakingChange("/pet", HttpMethod.POST, "id", "integer", "string");
        Collection<BreakingChange> expected = Arrays.asList(bc1, bc2);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(2);
        assertThat(result).hasSameElementsAs(expected);
    }
}