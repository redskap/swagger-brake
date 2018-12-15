package io.redskap.swagger.brake.integration.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.HttpMethod;
import io.redskap.swagger.brake.core.rule.response.ResponseTypeChangedBreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ResponseTypeChangedIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testResponseTypeChangeIsBreakingChangeWhenExistingAttributeRemoved() {
        // given
        String oldApiPath = "response/typechanged/petstore.yaml";
        String newApiPath = "response/typechanged/petstore_v2.yaml";
        ResponseTypeChangedBreakingChange bc1 = new ResponseTypeChangedBreakingChange("/pet/findByStatus", HttpMethod.GET, "200", "","array", "object");
        ResponseTypeChangedBreakingChange bc2 = new ResponseTypeChangedBreakingChange("/pet/findByTags", HttpMethod.GET, "200", "", "array", "string");
        Collection<BreakingChange> expected = Arrays.asList(bc1, bc2);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(2);
        assertThat(result).hasSameElementsAs(expected);
    }
}
