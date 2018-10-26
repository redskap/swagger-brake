package com.arnoldgalovics.blog.swagger.breaker.core.rule.response;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Path;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Response;
import com.arnoldgalovics.blog.swagger.breaker.core.model.SchemaRef;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Specification;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.BreakingChangeRule;
import org.springframework.stereotype.Component;

@Component
public class ResponseTypChangedRule implements BreakingChangeRule<ResponseTypeChangedBreakingChange> {
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
                        for (SchemaRef schemaRef : apiResponse.getSchemaRefs()) {
                            Optional<SchemaRef> newApiSchemaRef = newResponse.getSchemaRefByMediaType(schemaRef.getMediaType());
                            if (newApiSchemaRef.isPresent()) {
                                SchemaRef newSchemaRef = newApiSchemaRef.get();
                                String newSchemaTypeName = newSchemaRef.getSchemaTypeName();
                                if (!schemaRef.getSchemaTypeName().equals(newSchemaTypeName)) {
                                    breakingChanges.add(createBreakingChange(path, apiResponse, schemaRef.getSchemaTypeName(), newSchemaTypeName));
                                }
                            }
                        }
                    }
                }
            }
        }
        return breakingChanges;
    }

    private ResponseTypeChangedBreakingChange createBreakingChange(Path path, Response apiResponse, String schemaTypeName, String newSchemaTypeName) {
        return new ResponseTypeChangedBreakingChange(path.getPath(), path.getMethod(), apiResponse.getCode(), schemaTypeName, newSchemaTypeName);
    }
}
