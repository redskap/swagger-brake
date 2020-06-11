package io.redskap.swagger.brake.maven;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class DownloadOptions {
    private String repoUrl;
    private String snapshotRepoUrl;
    private String groupId;
    private String artifactId;
    private String username;
    private String password;
    private String currentArtifactVersion;

    public boolean isAuthenticationNeeded() {
        return StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password);
    }
}
