package io.redskap.swagger.brake.integration.beta;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.HttpMethod;
import io.redskap.swagger.brake.core.rule.beta.StandardApiToBetaApiBreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BetaApiIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testBetaApiAdditionDoesntTriggerABreak() {
        // given
        String oldApiPath = "beta/betaapiadded/petstore.yaml";
        String newApiPath = "beta/betaapiadded/petstore_v2.yaml";
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void testBetaApiModificationDoesntTriggerABreak() {
        // given
        String oldApiPath = "beta/betaapimodified/petstore.yaml";
        String newApiPath = "beta/betaapimodified/petstore_v2.yaml";
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void testBetaApiToStandardApiDoesntTriggerABreak() {
        // given
        String oldApiPath = "beta/betaapitostandard/petstore.yaml";
        String newApiPath = "beta/betaapitostandard/petstore_v2.yaml";
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void testBetaApiRemovalDoesntTriggerABreak() {
        // given
        String oldApiPath = "beta/betaapiremoved/petstore.yaml";
        String newApiPath = "beta/betaapiremoved/petstore_v2.yaml";
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void testStandardApiToBetaApiTriggersABreak() {
        // given
        String oldApiPath = "beta/standardapitobeta/petstore.yaml";
        String newApiPath = "beta/standardapitobeta/petstore_v2.yaml";
        BreakingChange bc1 = new StandardApiToBetaApiBreakingChange("/pet", HttpMethod.POST);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result).containsExactly(bc1);
    }
}
