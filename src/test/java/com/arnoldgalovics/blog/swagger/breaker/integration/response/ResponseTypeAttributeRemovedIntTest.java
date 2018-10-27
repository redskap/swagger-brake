package com.arnoldgalovics.blog.swagger.breaker.integration.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.model.HttpMethod;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.request.RequestTypeAttributeRemovedBreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.response.ResponseTypeAttributeRemovedBreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.integration.AbstractSwaggerBreakerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ResponseTypeAttributeRemovedIntTest extends AbstractSwaggerBreakerTest {
    @Test
    public void testResponseTypeChangeIsBreakingChangeWhenExistingAttributeRemoved() {
        // given
        String oldApiPath = "response/attributeremoved/petstore.yaml";
        String newApiPath = "response/attributeremoved/petstore_v2.yaml";
        ResponseTypeAttributeRemovedBreakingChange bc1 = new ResponseTypeAttributeRemovedBreakingChange("/pet/findByStatus", HttpMethod.GET, "200", "category");
        ResponseTypeAttributeRemovedBreakingChange bc2 = new ResponseTypeAttributeRemovedBreakingChange("/pet/findByStatus", HttpMethod.GET, "200", "category.id");
        ResponseTypeAttributeRemovedBreakingChange bc3 = new ResponseTypeAttributeRemovedBreakingChange("/pet/findByStatus", HttpMethod.GET, "200", "category.name");
        ResponseTypeAttributeRemovedBreakingChange bc4 = new ResponseTypeAttributeRemovedBreakingChange("/pet/{petId}", HttpMethod.GET, "200", "category");
        ResponseTypeAttributeRemovedBreakingChange bc5 = new ResponseTypeAttributeRemovedBreakingChange("/pet/{petId}", HttpMethod.GET, "200", "category.id");
        ResponseTypeAttributeRemovedBreakingChange bc6 = new ResponseTypeAttributeRemovedBreakingChange("/pet/{petId}", HttpMethod.GET, "200", "category.name");
        ResponseTypeAttributeRemovedBreakingChange bc7 = new ResponseTypeAttributeRemovedBreakingChange("/pet/findByTags", HttpMethod.GET, "200", "category");
        ResponseTypeAttributeRemovedBreakingChange bc8 = new ResponseTypeAttributeRemovedBreakingChange("/pet/findByTags", HttpMethod.GET, "200", "category.id");
        ResponseTypeAttributeRemovedBreakingChange bc9 = new ResponseTypeAttributeRemovedBreakingChange("/pet/findByTags", HttpMethod.GET, "200", "category.name");
        RequestTypeAttributeRemovedBreakingChange bc10 = new RequestTypeAttributeRemovedBreakingChange("/pet", HttpMethod.PUT, "category");
        RequestTypeAttributeRemovedBreakingChange bc11 = new RequestTypeAttributeRemovedBreakingChange("/pet", HttpMethod.PUT, "category.id");
        RequestTypeAttributeRemovedBreakingChange bc12 = new RequestTypeAttributeRemovedBreakingChange("/pet", HttpMethod.PUT, "category.name");
        RequestTypeAttributeRemovedBreakingChange bc13 = new RequestTypeAttributeRemovedBreakingChange("/pet", HttpMethod.POST, "category");
        RequestTypeAttributeRemovedBreakingChange bc14 = new RequestTypeAttributeRemovedBreakingChange("/pet", HttpMethod.POST, "category.id");
        RequestTypeAttributeRemovedBreakingChange bc15 = new RequestTypeAttributeRemovedBreakingChange("/pet", HttpMethod.POST, "category.name");
        Collection<BreakingChange> expected = Arrays.asList(bc1, bc2, bc3, bc4, bc5, bc6, bc7, bc8, bc9, bc10, bc11, bc12, bc13, bc14, bc15);
        // when
        Collection<BreakingChange> result = underTest.execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(15);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testResponseTypeChangeIsBreakingChangeWhenExistingDeepAttributeRemoved() {
        // given
        String oldApiPath = "response/deepattributeremoved/petstore.yaml";
        String newApiPath = "response/deepattributeremoved/petstore_v2.yaml";
        ResponseTypeAttributeRemovedBreakingChange bc1 = new ResponseTypeAttributeRemovedBreakingChange("/pet/findByStatus", HttpMethod.GET, "200", "category.name");
        ResponseTypeAttributeRemovedBreakingChange bc2 = new ResponseTypeAttributeRemovedBreakingChange("/pet/{petId}", HttpMethod.GET, "200", "category.name");
        ResponseTypeAttributeRemovedBreakingChange bc3 = new ResponseTypeAttributeRemovedBreakingChange("/pet/findByTags", HttpMethod.GET, "200", "category.name");
        RequestTypeAttributeRemovedBreakingChange bc4 = new RequestTypeAttributeRemovedBreakingChange("/pet", HttpMethod.PUT, "category.name");
        RequestTypeAttributeRemovedBreakingChange bc5 = new RequestTypeAttributeRemovedBreakingChange("/pet", HttpMethod.POST, "category.name");
        Collection<BreakingChange> expected = Arrays.asList(bc1, bc2, bc3, bc4, bc5);
        // when
        Collection<BreakingChange> result = underTest.execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(5);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testResponseTypeChangeIsNotBreakingChangeWhenDifferentTypeIsUsedButSameAttributes() {
        // given
        String oldApiPath = "response/differenttypesameattributes/petstore.yaml";
        String newApiPath = "response/differenttypesameattributes/petstore_v2.yaml";
        // when
        Collection<BreakingChange> result = underTest.execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(0);
    }

    @Test
    public void testResponseTypeChangeIsBreakingChangeWhenDifferentTypeIsUsedWithDifferentAttributes() {
        // given
        String oldApiPath = "response/differenttypesdifferentattributes/petstore.yaml";
        String newApiPath = "response/differenttypesdifferentattributes/petstore_v2.yaml";
        ResponseTypeAttributeRemovedBreakingChange bc1 = new ResponseTypeAttributeRemovedBreakingChange("/pet/findByStatus", HttpMethod.GET, "200", "category");
        ResponseTypeAttributeRemovedBreakingChange bc2 = new ResponseTypeAttributeRemovedBreakingChange("/pet/findByStatus", HttpMethod.GET, "200", "category.id");
        ResponseTypeAttributeRemovedBreakingChange bc3 = new ResponseTypeAttributeRemovedBreakingChange("/pet/findByStatus", HttpMethod.GET, "200", "category.name");
        Collection<BreakingChange> expected = Arrays.asList(bc1, bc2, bc3);
        // when
        Collection<BreakingChange> result = underTest.execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(3);
        assertThat(result).hasSameElementsAs(expected);
    }
}