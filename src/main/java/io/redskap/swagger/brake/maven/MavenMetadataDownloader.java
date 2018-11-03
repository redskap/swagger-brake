package io.redskap.swagger.brake.maven;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Component;

@Component
public class MavenMetadataDownloader {
    public MavenMetadata download(String baseUrl) {
        try {
            URL url = new URL(baseUrl + "/maven-metadata.xml");
            return (MavenMetadata) JAXBContext.newInstance(MavenMetadata.class).createUnmarshaller().unmarshal(url);
        } catch (JAXBException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
