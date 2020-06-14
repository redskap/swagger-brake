package io.redskap.swagger.brake.cli.options;

import static org.assertj.core.api.Assertions.assertThat;

import io.redskap.swagger.brake.runner.Options;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CliOptionsValidatorTest {
    private CliOptionsValidator underTest = new CliOptionsValidator();

    @Test
    public void testValidateThrowsExceptionWhenNewApiPathIsNotSet() {
        // given
        Options options = new Options();
        options.setOldApiPath("something");
        // when
        Throwable result = Assertions.catchThrowable(() -> underTest.validate(options));
        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
        assertThat(result.getMessage()).contains(CliOption.NEW_API_PATH.asCliOption());
    }

    @Test
    public void testValidateThrowsExceptionWhenNeitherOldApiPathNorMavenIsSet() {
        // given
        Options options = new Options();
        options.setNewApiPath("something");
        // when
        Throwable result = Assertions.catchThrowable(() -> underTest.validate(options));
        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
        assertThat(result.getMessage()).contains(CliOption.OLD_API_PATH.asCliOption());
        assertThat(result.getMessage()).containsIgnoringCase("maven");
    }

    @Test
    public void testValidateWorksWhenOldAndNewApiIsSet() {
        // given
        Options options = new Options();
        options.setNewApiPath("something");
        options.setOldApiPath("something");
        // when
        underTest.validate(options);
        // then no exception
    }

    @Test
    public void testValidateThrowsExceptionWhenMavenRepoUrlIsSetButSnapshotRepoIsNot() {
        // given
        Options options = new Options();
        options.setNewApiPath("something");
        options.setMavenRepoUrl("localhost:8080/repo");
        options.setCurrentArtifactVersion("1.0.0-SNAPSHOT");
        options.setGroupId("io.redskap");
        options.setArtifactId("swagger-brake");
        // when
        Throwable result = Assertions.catchThrowable(() -> underTest.validate(options));
        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
        assertThat(result.getMessage()).contains(CliOption.MAVEN_SNAPSHOT_REPO_URL.asCliOption());
    }

    @Test
    public void testValidateThrowsExceptionWhenMavenSnapshotRepoUrlIsSetButReleaseRepoIsNot() {
        // given
        Options options = new Options();
        options.setNewApiPath("something");
        options.setMavenSnapshotRepoUrl("localhost:8080/repo");
        options.setCurrentArtifactVersion("1.0.0-SNAPSHOT");
        options.setGroupId("io.redskap");
        options.setArtifactId("swagger-brake");
        // when
        Throwable result = Assertions.catchThrowable(() -> underTest.validate(options));
        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
        assertThat(result.getMessage()).contains(CliOption.MAVEN_REPO_URL.asCliOption());
    }

    @Test
    public void testValidateThrowsExceptionWhenMavenConfigIsSetExceptCurrentArtifactVersion() {
        // given
        Options options = new Options();
        options.setNewApiPath("something");
        options.setMavenRepoUrl("localhost:8080/repo");
        options.setMavenSnapshotRepoUrl("localhost:8080/snapshot-repo");
        options.setGroupId("io.redskap");
        options.setArtifactId("swagger-brake");
        // when
        Throwable result = Assertions.catchThrowable(() -> underTest.validate(options));
        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
        assertThat(result.getMessage()).contains(CliOption.CURRENT_ARTIFACT_VERSION.asCliOption());
    }

    @Test
    public void testValidateThrowsExceptionWhenMavenConfigIsSetExceptGroupId() {
        // given
        Options options = new Options();
        options.setNewApiPath("something");
        options.setMavenRepoUrl("localhost:8080/repo");
        options.setMavenSnapshotRepoUrl("localhost:8080/snapshot-repo");
        options.setCurrentArtifactVersion("1.0.0-SNAPSHOT");
        options.setArtifactId("swagger-brake");
        // when
        Throwable result = Assertions.catchThrowable(() -> underTest.validate(options));
        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
        assertThat(result.getMessage()).contains(CliOption.GROUP_ID.asCliOption());
    }

    @Test
    public void testValidateThrowsExceptionWhenMavenConfigIsSetExceptArtifactId() {
        // given
        Options options = new Options();
        options.setNewApiPath("something");
        options.setMavenRepoUrl("localhost:8080/repo");
        options.setMavenSnapshotRepoUrl("localhost:8080/snapshot-repo");
        options.setCurrentArtifactVersion("1.0.0-SNAPSHOT");
        options.setGroupId("io.redskap");
        // when
        Throwable result = Assertions.catchThrowable(() -> underTest.validate(options));
        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
        assertThat(result.getMessage()).contains(CliOption.ARTIFACT_ID.asCliOption());
    }

    @Test
    public void testValidateShouldNotThrowExceptionWhenMavenIsConfigured() {
        // given
        Options options = new Options();
        options.setNewApiPath("something");
        options.setMavenRepoUrl("localhost:8080/repo");
        options.setMavenSnapshotRepoUrl("localhost:8080/snapshot-repo");
        options.setCurrentArtifactVersion("1.0.0-SNAPSHOT");
        options.setGroupId("io.redskap");
        options.setArtifactId("swagger-brake");
        // when
        underTest.validate(options);
        // then no exception
    }

    @Test
    public void testValidateThrowsExceptionWhenOldApiPathAndMavenIsConfigured() {
        // given
        Options options = new Options();
        options.setOldApiPath("somethingelse");
        options.setNewApiPath("something");
        options.setMavenRepoUrl("localhost:8080/repo");
        options.setMavenSnapshotRepoUrl("localhost:8080/snapshot-repo");
        options.setCurrentArtifactVersion("1.0.0-SNAPSHOT");
        options.setGroupId("io.redskap");
        options.setArtifactId("swagger-brake");
        // when
        Throwable result = Assertions.catchThrowable(() -> underTest.validate(options));
        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
        assertThat(result.getMessage()).contains(CliOption.OLD_API_PATH.asCliOption());
        assertThat(result.getMessage()).containsIgnoringCase("maven");
    }
}