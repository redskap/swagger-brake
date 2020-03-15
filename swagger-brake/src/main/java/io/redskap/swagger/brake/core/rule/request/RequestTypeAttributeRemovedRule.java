package io.redskap.swagger.brake.core.rule.request;

import java.util.*;

import io.redskap.swagger.brake.core.model.*;
import io.redskap.swagger.brake.core.rule.BreakingChangeRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RequestTypeAttributeRemovedRule implements BreakingChangeRule<RequestTypeAttributeRemovedBreakingChange> {
    @Override
    public Collection<RequestTypeAttributeRemovedBreakingChange> checkRule(Specification oldApi, Specification newApi) {
        Set<RequestTypeAttributeRemovedBreakingChange> breakingChanges = new HashSet<>();
        for (Path path : oldApi.getPaths()) {
            if (path.isBetaApi()) {
                log.debug("Skipping {} as it's marked as a beta API", path);
                continue;
            }
            Optional<Path> newApiPath = newApi.getPath(path);
            if (newApiPath.isPresent()) {
                Path newPath = newApiPath.get();
                Optional<Request> requestBody = path.getRequestBody();
                Optional<Request> newRequestBody = newPath.getRequestBody();
                if (requestBody.isPresent() && newRequestBody.isPresent()) {
                    for (Map.Entry<MediaType, Schema> entry : requestBody.get().getMediaTypes().entrySet()) {
                        MediaType mediaType = entry.getKey();
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
