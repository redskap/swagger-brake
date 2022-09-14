package io.redskap.swagger.brake.integration.v3.request.requestbody.memory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MemoryIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testHugeFineractApi() {
        // given
        String oldApiPath = "swaggers/v3/memory/fineract/swagger_3.1.json";
        String newApiPath = "swaggers/v3/memory/fineract/swagger_3.2.json";
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(0);
    }
}