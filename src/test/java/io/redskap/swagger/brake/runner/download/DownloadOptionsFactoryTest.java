package io.redskap.swagger.brake.runner.download;

import static org.assertj.core.api.Assertions.assertThat;

import io.redskap.swagger.brake.maven.DownloadOptions;
import org.junit.Test;

public class DownloadOptionsFactoryTest {
    private DownloadOptionsFactory underTest = new DownloadOptionsFactory();
    
    @Test
    public void testCreateShouldWorkProperly() {
        // given
        String mavenRepoUrl = "a";
        String groupId = "b";
        String artifactId = "c";
        String mavenRepoUsername = "d";
        String mavenRepoPassword = "e";
        // when
        DownloadOptions result = underTest.create(mavenRepoUrl, groupId, artifactId, mavenRepoUsername, mavenRepoPassword);
        // then
        assertThat(result).extracting(DownloadOptions::getRepoUrl).isEqualTo(mavenRepoUrl);
        assertThat(result).extracting(DownloadOptions::getGroupId).isEqualTo(groupId);
        assertThat(result).extracting(DownloadOptions::getArtifactId).isEqualTo(artifactId);
        assertThat(result).extracting(DownloadOptions::getUsername).isEqualTo(mavenRepoUsername);
        assertThat(result).extracting(DownloadOptions::getPassword).isEqualTo(mavenRepoPassword);
    }
}