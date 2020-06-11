package io.redskap.swagger.brake.maven.maven2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArtifactVersionDeciderTest {
    @Test
    public void testIsSnapshotReturnsTrueWhenVersionIsEndingWithSnapshot() {
        // given
        // when
        boolean result = ArtifactVersionDecider.isSnapshot("1.0.0-SNAPSHOT");
        // then
        assertTrue(result);
    }

    @Test
    public void testIsSnapshotReturnsFalseWhenVersionIsNotEndingWithSnapshot() {
        // given
        // when
        boolean result = ArtifactVersionDecider.isSnapshot("1.0.0");
        // then
        assertFalse(result);
    }

    @Test
    public void testIsSnapshotReturnsFalseWhenVersionIsCompletelyRandom() {
        // given
        // when
        boolean result = ArtifactVersionDecider.isSnapshot("something-else-that-is-not-a-version");
        // then
        assertFalse(result);
    }
}