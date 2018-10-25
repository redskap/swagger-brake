package com.arnoldgalovics.blog.swagger.breaker.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.path.PathDeletedBreakingChange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PathDeletionIntTest extends AbstractSwaggerBreakerTest {
    @Test
    public void testPathDeletionWorksCorrectly() {
        // given
        PathDeletedBreakingChange expected = new PathDeletedBreakingChange("/pet/findByStatus", "GET");
        // when
        Collection<BreakingChange> result = underTest.execute("pathdeletion/petstore.yaml", "pathdeletion/petstore_v2.yaml");
        // then
        assertThat(result).hasSize(1);
        assertThat(result.iterator().next()).isEqualTo(expected);
    }
}