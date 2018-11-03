package io.redskap.swagger.brake.maven;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "snapshot")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MavenSnapshot {
    private String timestamp;
    private String buildNumber;
}
