package io.redskap.swagger.brake.maven.model;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "versioning")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MavenVersioning {
    private String latest;
    private MavenSnapshot snapshot;
}
