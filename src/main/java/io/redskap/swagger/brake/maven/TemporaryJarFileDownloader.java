package io.redskap.swagger.brake.maven;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

import io.redskap.swagger.brake.maven.http.HttpRequestFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class TemporaryJarFileDownloader {
    private final HttpClient httpClient;

    File download(HttpUriRequest httpRequest, String groupId, String artifactId, String version) {
        try {
            File destination = Files.createTempFile("swagger-brake", groupId + "-" + artifactId + "-" + version + ".jar").toFile();
            HttpResponse response = httpClient.execute(httpRequest);
            FileUtils.copyInputStreamToFile(response.getEntity().getContent(), destination);
            return destination;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
