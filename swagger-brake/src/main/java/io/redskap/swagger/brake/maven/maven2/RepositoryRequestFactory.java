package io.redskap.swagger.brake.maven.maven2;

import io.redskap.swagger.brake.maven.DownloadOptions;
import io.redskap.swagger.brake.maven.http.HttpRequestFactory;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RepositoryRequestFactory {
    private final HttpRequestFactory requestFactory;

    public HttpUriRequest create(String url, DownloadOptions options) {
        try {
            if (options.isAuthenticationNeeded()) {
                return requestFactory.authenticatedGet(url, options.getUsername(), options.getPassword());
            } else {
                return requestFactory.get(url);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while creating the http request", e);
        }
    }
}
