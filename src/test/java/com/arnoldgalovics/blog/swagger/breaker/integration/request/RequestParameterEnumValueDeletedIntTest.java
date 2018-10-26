package com.arnoldgalovics.blog.swagger.breaker.integration.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Collections;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.model.HttpMethod;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.request.RequestParameterEnumValueDeletedBreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.integration.AbstractSwaggerBreakerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RequestParameterEnumValueDeletedIntTest extends AbstractSwaggerBreakerTest {
    @Test
    public void testResponseTypeChangeIsBreakingChangeWhenExistingAttributeRemoved() {
        // given
        String oldApiPath = "request/parameterenumvaluedeleted/petstore.yaml";
        String newApiPath = "request/parameterenumvaluedeleted/petstore_v2.yaml";
        RequestParameterEnumValueDeletedBreakingChange bc = new RequestParameterEnumValueDeletedBreakingChange("/pet/findByStatus", HttpMethod.GET, "status", "pending");
        Collection<BreakingChange> expected = Collections.singleton(bc);
        // when
        Collection<BreakingChange> result = underTest.execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }
}
