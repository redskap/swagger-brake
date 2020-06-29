package io.redskap.swagger.brake.integration.v3.request.requestbody.anyof;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Collections;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.HttpMethod;
import io.redskap.swagger.brake.core.rule.request.RequestTypeAttributeRemovedBreakingChange;
import io.redskap.swagger.brake.core.rule.request.RequestTypeChangedBreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class V3RequestBodyAnyOfIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testV3RequestBodyAnyOfPropertyRemovedIsBreakingChange() {
        // given
        String oldApiPath = "swaggers/v3/request/requestbody/anyof/propertydeleted/petstore.yaml";
        String newApiPath = "swaggers/v3/request/requestbody/anyof/propertydeleted/petstore_v2.yaml";
        RequestTypeAttributeRemovedBreakingChange breakingChange = new RequestTypeAttributeRemovedBreakingChange("/pet", HttpMethod.PATCH, "id");
        Collection<BreakingChange> expected = Collections.singletonList(breakingChange);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testV3RequestBodyAnyOfPropertyTypeChangedIsBreakingChange() {
        // given
        String oldApiPath = "swaggers/v3/request/requestbody/anyof/propertytypechanged/petstore.yaml";
        String newApiPath = "swaggers/v3/request/requestbody/anyof/propertytypechanged/petstore_v2.yaml";
        RequestTypeChangedBreakingChange breakingChange = new RequestTypeChangedBreakingChange("/pet", HttpMethod.PATCH, "id", "integer", "string");
        Collection<BreakingChange> expected = Collections.singletonList(breakingChange);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }
}