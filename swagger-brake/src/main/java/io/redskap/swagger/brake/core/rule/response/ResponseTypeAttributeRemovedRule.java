package io.redskap.swagger.brake.core.rule.response;

import java.util.*;

import io.redskap.swagger.brake.core.model.Path;
import io.redskap.swagger.brake.core.model.Response;
import io.redskap.swagger.brake.core.model.Schema;
import io.redskap.swagger.brake.core.model.Specification;
import io.redskap.swagger.brake.core.rule.BreakingChangeRule;
import org.springframework.stereotype.Component;

@Component
public class ResponseTypeAttributeRemovedRule implements BreakingChangeRule<ResponseTypeAttributeRemovedBreakingChange> {
    @Override
    public Collection<ResponseTypeAttributeRemovedBreakingChange> checkRule(Specification oldApi, Specification newApi) {
        Set<ResponseTypeAttributeRemovedBreakingChange> breakingChanges = new HashSet<>();
        for (Path path : oldApi.getPaths()) {
            Optional<Path> newApiPath = newApi.getPath(path);
            if (newApiPath.isPresent()) {
                Path newPath = newApiPath.get();
                for (Response apiResponse : path.getResponses()) {
                    Optional<Response> newApiResponse = newPath.getResponseByCode(apiResponse.getCode());
                    if (newApiResponse.isPresent()) {
                        Response newResponse = newApiResponse.get();
                        for (Map.Entry<String, Schema> entry : apiResponse.getMediaTypes().entrySet()) {
                            String mediaType = entry.getKey();
                            Schema schema = entry.getValue();
                            Optional<Schema> newApiSchema = newResponse.getSchemaByMediaType(mediaType);
                            if (newApiSchema.isPresent()) {
                                Schema newSchema = newApiSchema.get();
                                Collection<String> oldAttributeNames = schema.getAttributeNames();
                                Collection<String> newAttributeNames = newSchema.getAttributeNames();
                                for (String oldAttributeName : oldAttributeNames) {
                                    if (!newAttributeNames.contains(oldAttributeName)) {
                                        breakingChanges.add(
                                            new ResponseTypeAttributeRemovedBreakingChange(path.getPath(), path.getMethod(), apiResponse.getCode(), oldAttributeName));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return breakingChanges;
    }
}
