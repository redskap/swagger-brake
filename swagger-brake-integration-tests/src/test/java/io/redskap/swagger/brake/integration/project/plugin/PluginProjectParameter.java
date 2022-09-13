package io.redskap.swagger.brake.integration.project.plugin;

import io.redskap.swagger.brake.integration.project.ProjectParameter;
import io.redskap.swagger.brake.integration.support.SwaggerBrakeVersion;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PluginProjectParameter extends ProjectParameter {
    private SwaggerBrakeVersion swaggerBrakeVersion;
    private boolean removePackaging = false;
    private String artifactId;
}
