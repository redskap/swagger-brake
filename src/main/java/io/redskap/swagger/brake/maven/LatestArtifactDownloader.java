package io.redskap.swagger.brake.maven;

import java.io.File;

public interface LatestArtifactDownloader {
    File download(DownloadOptions options);
}
