package io.redskap.swagger.brake.integration.v2.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Collections;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.HttpMethod;
import io.redskap.swagger.brake.core.model.MediaType;
import io.redskap.swagger.brake.core.rule.response.ResponseMediaTypeDeletedBreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ResponseMediaTypeDeletedIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testResponseMediaTypeDeletedWorksCorrectly() {
        // given
        String oldApiPath = "swaggers/v2/response/mediatypedeleted/petstore.yaml";
        String newApiPath = "swaggers/v2/response/mediatypedeleted/petstore_v2.yaml";
        ResponseMediaTypeDeletedBreakingChange bc = new ResponseMediaTypeDeletedBreakingChange("/pet/findByStatus", HttpMethod.GET, new MediaType("application/json"));
        Collection<BreakingChange> expected = Collections.singleton(bc);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }
}
