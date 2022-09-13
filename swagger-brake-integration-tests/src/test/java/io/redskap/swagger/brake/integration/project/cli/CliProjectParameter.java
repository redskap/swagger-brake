package io.redskap.swagger.brake.integration.project.cli;

import io.redskap.swagger.brake.integration.project.ProjectParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CliProjectParameter extends ProjectParameter {
    private String groupId;
    private String artifactId;
}
