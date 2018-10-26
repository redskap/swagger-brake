package com.arnoldgalovics.blog.swagger.breaker.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.response.ResponseTypeAttributeRemovedBreakingChange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ResponseTypeChangeIntTest extends AbstractSwaggerBreakerTest {
    @Test
    public void testResponseTypeChangeIsBreakingChangeWhenExistingAttributeRemoved() {
        // given
        String oldApiPath = "responetypechange/attributeremoved/petstore.yaml";
        String newApiPath = "responetypechange/attributeremoved/petstore_v2.yaml";
        ResponseTypeAttributeRemovedBreakingChange expected = new ResponseTypeAttributeRemovedBreakingChange("Pet", "category");
        // when
        Collection<BreakingChange> result = underTest.execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result.iterator().next()).isEqualTo(expected);
    }

    @Test
    public void testResponseTypeChangeIsNotBreakingChangeWhenDifferentTypeIsUsedButSameAttributes() {
        // given
        String oldApiPath = "responetypechange/differenttypesameattributes/petstore.yaml";
        String newApiPath = "responetypechange/differenttypesameattributes/petstore_v2.yaml";
        // when
        Collection<BreakingChange> result = underTest.execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(0);
    }

    @Test
    public void testResponseTypeChangeIsBreakingChangeWhenDifferentTypeIsUsedWithDifferentAttributes() {
        // given
        String oldApiPath = "responetypechange/differenttypesdifferentattributes/petstore.yaml";
        String newApiPath = "responetypechange/differenttypesdifferentattributes/petstore_v2.yaml";
        ResponseTypeAttributeRemovedBreakingChange expected = new ResponseTypeAttributeRemovedBreakingChange("Pet", "category");
        // when
        Collection<BreakingChange> result = underTest.execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result.iterator().next()).isEqualTo(expected);
    }
}