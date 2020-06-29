package io.redskap.swagger.brake.integration.v3.request.requestbody.allof;

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
public class V3RequestBodyAllOfIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testV3RequestBodyAllOfPropertyRemovedIsBreakingChange() {
        // given
        String oldApiPath = "swaggers/v3/request/requestbody/allof/propertydeleted/petstore.yaml";
        String newApiPath = "swaggers/v3/request/requestbody/allof/propertydeleted/petstore_v2.yaml";
        RequestTypeAttributeRemovedBreakingChange breakingChange = new RequestTypeAttributeRemovedBreakingChange("/pet", HttpMethod.PATCH, "bark");
        Collection<BreakingChange> expected = Collections.singletonList(breakingChange);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testV3RequestBodyAllOfPropertyTypeChangedIsBreakingChange() {
        // given
        String oldApiPath = "swaggers/v3/request/requestbody/allof/propertytypechanged/petstore.yaml";
        String newApiPath = "swaggers/v3/request/requestbody/allof/propertytypechanged/petstore_v2.yaml";
        RequestTypeChangedBreakingChange breakingChange = new RequestTypeChangedBreakingChange("/pet", HttpMethod.PATCH, "bark", "integer", "string");
        Collection<BreakingChange> expected = Collections.singletonList(breakingChange);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }
}