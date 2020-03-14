package io.redskap.swagger.brake.maven.jar.filename;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ConfigurableApiFileNameCheckerTest {
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowExceptionIfNullParameterIsGiven() {
        // given
        // when
        new ConfigurableApiFileNameChecker(null);
        // then exception thrown
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowExceptionIfEmptyStringParameterIsGiven() {
        // given
        // when
        new ConfigurableApiFileNameChecker("  ");
        // then exception thrown
    }

    @Test
    public void testIsApiShouldReturnFalseWhenDoesntMatch() {
        // given
        ConfigurableApiFileNameChecker underTest = new ConfigurableApiFileNameChecker("openapi");
        // when
        boolean result = underTest.isApiFile("c:/api/swagger.yaml");
        // then
        assertThat(result).isFalse();
    }

    @Test
    public void testIsApiShouldReturnTrueWhenYamlMatchesFullPath() {
        // given
        ConfigurableApiFileNameChecker underTest = new ConfigurableApiFileNameChecker("openapi");
        // when
        boolean result = underTest.isApiFile("c:/api/openapi.yaml");
        // then
        assertThat(result).isTrue();
    }

    @Test
    public void testIsApiShouldReturnTrueWhenYamlMatches() {
        // given
        ConfigurableApiFileNameChecker underTest = new ConfigurableApiFileNameChecker("openapi");
        // when
        boolean result = underTest.isApiFile("openapi.yaml");
        // then
        assertThat(result).isTrue();
    }

    @Test
    public void testIsApiShouldReturnTrueWhenYmlMatches() {
        // given
        ConfigurableApiFileNameChecker underTest = new ConfigurableApiFileNameChecker("openapi");
        // when
        boolean result = underTest.isApiFile("openapi.yml");
        // then
        assertThat(result).isTrue();
    }

    @Test
    public void testIsApiShouldReturnTrueWhenJsonMatches() {
        // given
        ConfigurableApiFileNameChecker underTest = new ConfigurableApiFileNameChecker("openapi");
        // when
        boolean result = underTest.isApiFile("openapi.json");
        // then
        assertThat(result).isTrue();
    }

    @Test
    public void testIsApiShouldReturnTrueWhenExtensionIsProvidedAndMatches() {
        // given
        ConfigurableApiFileNameChecker underTest = new ConfigurableApiFileNameChecker("openapi.txt");
        // when
        boolean result = underTest.isApiFile("openapi.txt");
        // then
        assertThat(result).isTrue();
    }
}