package io.redskap.swagger.brake.integration.v2.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EmptyResponseIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testEmptyResponseWorks() {
        // given
        String apiPath = "swaggers/v2/response/emptyresponse/petstore.yaml";

        // when
        Collection<BreakingChange> result = execute(apiPath, apiPath);
        // then
        assertThat(result).isEmpty();
    }
}
