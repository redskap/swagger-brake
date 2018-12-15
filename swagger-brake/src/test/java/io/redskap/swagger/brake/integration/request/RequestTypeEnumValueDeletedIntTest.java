package io.redskap.swagger.brake.integration.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.HttpMethod;
import io.redskap.swagger.brake.core.rule.request.RequestTypeEnumValueDeletedBreakingChange;
import io.redskap.swagger.brake.core.rule.response.ResponseTypeEnumValueDeletedBreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RequestTypeEnumValueDeletedIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testRequestTypeEnumValueDeletedWorksCorrectly() {
        // given
        String oldApiPath = "request/typeenumvaluedeleted/petstore.yaml";
        String newApiPath = "request/typeenumvaluedeleted/petstore_v2.yaml";
        RequestTypeEnumValueDeletedBreakingChange bc1 = new RequestTypeEnumValueDeletedBreakingChange("/store/order", HttpMethod.POST, "status.approved");
        ResponseTypeEnumValueDeletedBreakingChange bc2 = new ResponseTypeEnumValueDeletedBreakingChange("/store/order", HttpMethod.POST, "status.approved");
        ResponseTypeEnumValueDeletedBreakingChange bc3 = new ResponseTypeEnumValueDeletedBreakingChange("/store/order/{orderId}", HttpMethod.GET, "status.approved");
        Collection<BreakingChange> expected = Arrays.asList(bc1, bc2, bc3);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(3);
        assertThat(result).hasSameElementsAs(expected);
    }
}
