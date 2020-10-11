package io.redskap.swagger.brake.integration.v2.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Collections;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.HttpMethod;
import io.redskap.swagger.brake.core.rule.response.ResponseTypeAttributeRemovedBreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RecursiveTypeAttributeRemovedIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testResponseTypeChangeIsBreakingChangeWhenExistingAttributeRemoved() {
        // given
        String oldApiPath = "swaggers/v2/response/recursiveresponseattributeremoved/schema.json";
        String newApiPath = "swaggers/v2/response/recursiveresponseattributeremoved/schema_v2.json";
        ResponseTypeAttributeRemovedBreakingChange bc1 =
            new ResponseTypeAttributeRemovedBreakingChange("/api/v1/audits/summary/{businessId}", HttpMethod.GET, "200", "unverifiedPayoffBreakdown.amountApplied");
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }
}
