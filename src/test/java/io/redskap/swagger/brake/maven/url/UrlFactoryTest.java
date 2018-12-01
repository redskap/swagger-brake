package io.redskap.swagger.brake.maven.url;

import static org.assertj.core.api.Assertions.assertThat;

import io.redskap.swagger.brake.maven.DownloadOptions;
import org.junit.Test;

public class UrlFactoryTest {
    private UrlFactory underTest = new UrlFactory();

    @Test
    public void testCreateLatestArtifactSnapshotMetadataUrlShouldWorkProperly() {
        // given
        DownloadOptions options = new DownloadOptions();
        options.setGroupId("io.swagger.brake");
        options.setArtifactId("swagger-brake-example");
        options.setRepoUrl("http://localhost:8081/artifactory/libs-snapshot-local");
        String latestVersion = "1.2.0-SNAPSHOT";

        String expected = "http://localhost:8081/artifactory/libs-snapshot-local/io/swagger/brake/swagger-brake-example/1.2.0-SNAPSHOT/maven-metadata.xml";
        // when
        String result = underTest.createLatestArtifactSnapshotMetadataUrl(options, latestVersion);
        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testCreateLatestArtifactVersionMetadataUrlShouldWorkProperly() {
        // given
        DownloadOptions options = new DownloadOptions();
        options.setGroupId("io.swagger.brake");
        options.setArtifactId("swagger-brake-example");
        options.setRepoUrl("http://localhost:8081/artifactory/libs-snapshot-local");

        String expected = "http://localhost:8081/artifactory/libs-snapshot-local/io/swagger/brake/swagger-brake-example/maven-metadata.xml";
        // when
        String result = underTest.createLatestArtifactVersionMetadataUrl(options);
        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testCreateLatestArtifactUrlShouldWorkProperly() {
        // given
        DownloadOptions options = new DownloadOptions();
        options.setGroupId("io.swagger.brake");
        options.setArtifactId("swagger-brake-example");
        options.setRepoUrl("http://localhost:8081/artifactory/libs-snapshot-local");
        String latestVersion = "1.2.0-SNAPSHOT";
        String latestSnapshotName = "swagger-brake-example-1.2.0-20181118.221307-1";

        String expected = "http://localhost:8081/artifactory/libs-snapshot-local/io/swagger/brake/swagger-brake-example/1.2.0-SNAPSHOT/swagger-brake-example-1.2.0-20181118.221307-1.jar";
        // when
        String result = underTest.createLatestArtifactUrl(options, latestVersion, latestSnapshotName);
        // then
        assertThat(result).isEqualTo(expected);
    }
}