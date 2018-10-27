package com.arnoldgalovics.blog.swagger.breaker.core.rule.response;

import java.util.*;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Path;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Response;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Schema;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Specification;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.BreakingChangeRule;
import org.springframework.stereotype.Component;

@Component
public class ResponseTypeChangedRule implements BreakingChangeRule<ResponseTypeChangedBreakingChange> {
    @Override
    public Collection<ResponseTypeChangedBreakingChange> checkRule(Specification oldApi, Specification newApi) {
        Set<ResponseTypeChangedBreakingChange> breakingChanges = new HashSet<>();
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
                                Map<String, String> newTypes = newSchema.getTypes();
                                for (Map.Entry<String, String> type : schema.getTypes().entrySet()) {
                                    String attribute = type.getKey();
                                    String typeName = type.getValue();
                                    String newType = newTypes.get(attribute);
                                    if (newType != null && !newType.equals(typeName)) {
                                        breakingChanges.add(
                                            new ResponseTypeChangedBreakingChange(path.getPath(), path.getMethod(), apiResponse.getCode(), attribute, typeName, newType));
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
