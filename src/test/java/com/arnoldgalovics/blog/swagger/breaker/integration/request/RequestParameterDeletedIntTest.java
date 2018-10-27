package com.arnoldgalovics.blog.swagger.breaker.integration.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Collections;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.model.HttpMethod;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.request.RequestParameterDeletedBreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.integration.AbstractSwaggerBreakerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RequestParameterDeletedIntTest extends AbstractSwaggerBreakerTest {
    @Test
    public void testRequestParameterDeletedWorksCorrectly() {
        // given
        String oldApiPath = "request/parameterdeleted/petstore.yaml";
        String newApiPath = "request/parameterdeleted/petstore_v2.yaml";
        RequestParameterDeletedBreakingChange bc = new RequestParameterDeletedBreakingChange("/pet/findByStatus", HttpMethod.GET, "status");
        Collection<BreakingChange> expected = Collections.singleton(bc);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }
}
