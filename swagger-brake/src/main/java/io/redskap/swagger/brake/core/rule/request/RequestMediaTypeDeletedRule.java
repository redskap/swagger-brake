package io.redskap.swagger.brake.core.rule.request;

import java.util.*;

import io.redskap.swagger.brake.core.model.Path;
import io.redskap.swagger.brake.core.model.Request;
import io.redskap.swagger.brake.core.model.Schema;
import io.redskap.swagger.brake.core.model.Specification;
import io.redskap.swagger.brake.core.rule.BreakingChangeRule;
import org.springframework.stereotype.Component;

@Component
public class RequestMediaTypeDeletedRule implements BreakingChangeRule<RequestMediaTypeDeletedBreakingChange> {
    @Override
    public Collection<RequestMediaTypeDeletedBreakingChange> checkRule(Specification oldApi, Specification newApi) {
        Set<RequestMediaTypeDeletedBreakingChange> breakingChanges = new HashSet<>();
        for (Path path : oldApi.getPaths()) {
            Optional<Path> newApiPath = newApi.getPath(path);
            if (newApiPath.isPresent()) {
                Path newPath = newApiPath.get();
                Optional<Request> requestBody = path.getRequestBody();
                Optional<Request> newRequestBody = newPath.getRequestBody();
                if (requestBody.isPresent() && newRequestBody.isPresent()) {
                    for (Map.Entry<String, Schema> entry : requestBody.get().getMediaTypes().entrySet()) {
                        String mediaType = entry.getKey();
                        if (!newRequestBody.get().getMediaTypes().containsKey(mediaType)) {
                            breakingChanges.add(new RequestMediaTypeDeletedBreakingChange(path.getPath(), path.getMethod(), mediaType));
                        }
                    }
                }
            }
        }
        return breakingChanges;
    }
}
