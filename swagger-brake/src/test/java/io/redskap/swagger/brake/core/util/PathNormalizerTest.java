package io.redskap.swagger.brake.core.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PathNormalizerTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNormalizePathSlashesThrowsExceptionWhenNullGiven() {
        // given
        // when
        PathNormalizer.normalizePathSlashes(null);
        // then exception thrown
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNormalizePathSlashesThrowsExceptionWhenBlankGiven() {
        // given
        // when
        PathNormalizer.normalizePathSlashes("   ");
        // then exception thrown
    }

    @Test
    public void testNormalizePathSlashesAddsNecessaryLeadingSlashesIfMissing() {
        // given
        // when
        String result = PathNormalizer.normalizePathSlashes("test/path");
        // then
        assertThat(result).isEqualTo("/test/path");
    }

    @Test
    public void testNormalizePathSlashesRemovesUnnecessaryTrailingSlashesIfAny() {
        // given
        // when
        String result = PathNormalizer.normalizePathSlashes("/test/path/");
        // then
        assertThat(result).isEqualTo("/test/path");
    }

    @Test
    public void testNormalizePathSlashesRemovesAndAddsSlashesWhereNeeded() {
        // given
        // when
        String result = PathNormalizer.normalizePathSlashes("test/path/");
        // then
        assertThat(result).isEqualTo("/test/path");
    }
}