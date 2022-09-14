package io.redskap.swagger.brake.integration.v2.nobreakingchange;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class NoBreakingChangeIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testNoBreakingChangeWorksCorrectly() {
        // given
        String oldApiPath = "swaggers/v2/nobreakingchange/petstore.yaml";
        String newApiPath = "swaggers/v2/nobreakingchange/petstore_v2.yaml";
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void testNoBreakingChangeWhenSameApiUsedWorksCorrectly() {
        // given
        String oldApiPath = "swaggers/v2/nobreakingchange/petstore.yaml";
        String newApiPath = "swaggers/v2/nobreakingchange/petstore.yaml";
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).isEmpty();
    }
}
