package io.redskap.swagger.brake.maven.model;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "metadata")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MavenMetadata {
    private String groupId;
    private String artifactId;
    private MavenVersioning versioning;
}
