package com.arnoldgalovics.blog.swagger.breaker.core.rule.request;

import java.util.*;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Path;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Request;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Schema;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Specification;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.BreakingChangeRule;
import org.springframework.stereotype.Component;

@Component
public class RequestTypeAttributeRemovedRule implements BreakingChangeRule<RequestTypeAttributeRemovedBreakingChange> {
    @Override
    public Collection<RequestTypeAttributeRemovedBreakingChange> checkRule(Specification oldApi, Specification newApi) {
        Set<RequestTypeAttributeRemovedBreakingChange> breakingChanges = new HashSet<>();
        for (Path path : oldApi.getPaths()) {
            Optional<Path> newApiPath = newApi.getPath(path);
            if (newApiPath.isPresent()) {
                Path newPath = newApiPath.get();
                Optional<Request> requestBody = path.getRequestBody();
                Optional<Request> newRequestBody = newPath.getRequestBody();
                if (requestBody.isPresent() && newRequestBody.isPresent()) {
                    for (Map.Entry<String, Schema> entry : requestBody.get().getMediaTypes().entrySet()) {
                        String mediaType = entry.getKey();
                        Schema schema = entry.getValue();
                        Optional<Schema> newApiSchema = newRequestBody.get().getSchemaByMediaType(mediaType);
                        if (newApiSchema.isPresent()) {
                            Schema newSchema = newApiSchema.get();
                            Collection<String> oldAttributeNames = schema.getAttributeNames();
                            Collection<String> newAttributeNames = newSchema.getAttributeNames();
                            for (String oldAttributeName : oldAttributeNames) {
                                if (!newAttributeNames.contains(oldAttributeName)) {
                                    breakingChanges.add(
                                        new RequestTypeAttributeRemovedBreakingChange(path.getPath(), path.getMethod(), oldAttributeName));
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
