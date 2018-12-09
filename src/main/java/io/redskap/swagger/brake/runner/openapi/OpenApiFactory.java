package io.redskap.swagger.brake.runner.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;
import org.springframework.stereotype.Component;

@Component
public class OpenApiFactory {
    public OpenAPI fromFile(String path) {
        try {
            ParseOptions parseOptions = new ParseOptions();
            parseOptions.setResolveFully(true);
            OpenAPI loadedApi = new OpenAPIV3Parser().read(path, null, parseOptions);
            if (loadedApi == null) {
                throw new IllegalStateException("API cannot be loaded from path " + path);
            }
            return loadedApi;
        } catch (Exception e) {
            throw new IllegalStateException("API cannot be loaded from path " + path);
        }
    }
}
