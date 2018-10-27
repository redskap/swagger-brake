package com.arnoldgalovics.blog.swagger.breaker.integration.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Collections;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.model.HttpMethod;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.request.RequestParameterInTypeChangedBreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.integration.AbstractSwaggerBreakerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RequestParameterInTypeChangedIntTest extends AbstractSwaggerBreakerTest {
    @Test
    public void testRequestParameterInTypeChangedWorksCorrectly() {
        // given
        String oldApiPath = "request/parameterintypechanged/petstore.yaml";
        String newApiPath = "request/parameterintypechanged/petstore_v2.yaml";
        RequestParameterInTypeChangedBreakingChange bc = new RequestParameterInTypeChangedBreakingChange("/pet/findByStatus", HttpMethod.GET, "status", "query", "header");
        Collection<BreakingChange> expected = Collections.singleton(bc);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }
}
