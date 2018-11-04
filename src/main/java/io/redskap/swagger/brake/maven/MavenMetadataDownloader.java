package io.redskap.swagger.brake.maven;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import io.redskap.swagger.brake.maven.model.MavenMetadata;
import io.redskap.swagger.brake.util.ExceptionUtils;
import org.springframework.stereotype.Component;

@Component
public class MavenMetadataDownloader {
    public MavenMetadata download(String baseUrl) {
        String metadataUrl = baseUrl + "/maven-metadata.xml";
        try {
            URL url = new URL(metadataUrl);
            return (MavenMetadata) JAXBContext.newInstance(MavenMetadata.class).createUnmarshaller().unmarshal(url);
        } catch (JAXBException | MalformedURLException e) {
            Optional<FileNotFoundException> nfe = ExceptionUtils.findInChain(e, FileNotFoundException.class);
            if (nfe.isPresent()) {
                throw new RuntimeException("Cannot download metadata from: " + metadataUrl, e);
            } else {
                throw new RuntimeException("Cannot get metadata", e);
            }
        }
    }
}
