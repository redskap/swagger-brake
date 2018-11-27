package io.redskap.swagger.brake.runner.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import org.springframework.stereotype.Component;

@Component
public class OpenApiFactory {
    public OpenAPI fromFile(String path) {
        try {
            OpenAPI loadedApi = new OpenAPIV3Parser().read(path);
            if (loadedApi == null) {
                throw new IllegalStateException("API cannot be loaded from path " + path);
            }
            return loadedApi;
        } catch (Exception e) {
            throw new IllegalStateException("API cannot be loaded from path " + path);
        }
    }
}
