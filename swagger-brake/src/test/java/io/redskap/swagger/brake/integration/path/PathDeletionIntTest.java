package io.redskap.swagger.brake.integration.path;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Collections;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.HttpMethod;
import io.redskap.swagger.brake.core.rule.path.PathDeletedBreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PathDeletionIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testPathDeletionWorksCorrectly() {
        // given
        String oldApiPath = "path/deleted/petstore.yaml";
        String newApiPath = "path/deleted/petstore_v2.yaml";
        PathDeletedBreakingChange bc = new PathDeletedBreakingChange("/pet/findByStatus", HttpMethod.GET);
        Collection<BreakingChange> expected = Collections.singleton(bc);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }
}