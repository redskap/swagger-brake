package io.redskap.swagger.brake.maven;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import io.redskap.swagger.brake.maven.exception.UnauthorizedException;
import io.redskap.swagger.brake.maven.model.MavenMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
class MavenMetadataDownloader {
    private final HttpClient httpClient;

    MavenMetadata download(HttpUriRequest httpRequest) {
        try {
            log.debug("Downloading maven metadata from {}", httpRequest.getURI());
            HttpResponse response = httpClient.execute(httpRequest);
            if (HttpStatus.SC_UNAUTHORIZED == response.getStatusLine().getStatusCode()) {
                throw new UnauthorizedException("Unauthorized maven metadata downloading from " + httpRequest.getURI());
            }
            // TODO: content type check would be great here
            return getMetadata(response);
        } catch (JAXBException | IOException e) {
            throw new RuntimeException("Cannot get metadata", e);
        }
    }

    private MavenMetadata getMetadata(HttpResponse response) throws IOException, JAXBException {
        InputStream content = response.getEntity().getContent();
        return (MavenMetadata) JAXBContext.newInstance(MavenMetadata.class).createUnmarshaller().unmarshal(content);
    }
}
