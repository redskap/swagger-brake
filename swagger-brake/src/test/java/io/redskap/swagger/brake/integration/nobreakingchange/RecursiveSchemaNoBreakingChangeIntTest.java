package io.redskap.swagger.brake.integration.nobreakingchange;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RecursiveSchemaNoBreakingChangeIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testNoBreakingChangeWorksCorrectly() {
        // given
        String oldApiPath = "nobreakingchange/recursive/schema.json";
        String newApiPath = "nobreakingchange/recursive/schema_v2.json";
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void testNoBreakingChangeWorksCorrectly2() {
        // given
        String oldApiPath = "nobreakingchange/recursive2/swagger.json";
        String newApiPath = "nobreakingchange/recursive2/swagger.json";
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).isEmpty();
    }
}

