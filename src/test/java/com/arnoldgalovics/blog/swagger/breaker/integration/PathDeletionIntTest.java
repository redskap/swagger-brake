package com.arnoldgalovics.blog.swagger.breaker.integration;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.path.PathDeletedBreakingChange;
import org.junit.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class PathDeletionIntTest extends AbstractSwaggerBreakerTest {
    @Test
    public void testPathDeletionWorksCorrectly() {
        // given
        PathDeletedBreakingChange expected = new PathDeletedBreakingChange("/pet/findByStatus", "GET");
        // when
        Collection<BreakingChange> result = underTest.execute();
        // then
        assertThat(result).hasSize(1);
        assertThat(result.iterator().next()).isEqualTo(expected);

    }

    @Override
    protected String getOldApiPath() {
        return "pathdeletion/petstore.yaml";
    }

    @Override
    protected String getNewApiPath() {
        return "pathdeletion/petstore_v2.yaml";
    }
}