package io.redskap.swagger.brake.maven;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class TemporaryJarFileDownloader {
    public File download(String groupId, String artifactId, String version, String url) {
        try {
            File destination = Files.createTempFile("swagger-brake", groupId + "-" + artifactId + "-" + version + ".jar").toFile();
            FileUtils.copyURLToFile(new URL(url), destination);
            return destination;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
