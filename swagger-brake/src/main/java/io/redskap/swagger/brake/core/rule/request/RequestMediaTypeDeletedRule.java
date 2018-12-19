package io.redskap.swagger.brake.core.rule.request;

import java.util.*;

import io.redskap.swagger.brake.core.model.*;
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
                    Request request = requestBody.get();
                    Request newRequest = newRequestBody.get();
                    checkMediaTypeBreaking(breakingChanges, path, request, newRequest);
                }
            }
        }
        return breakingChanges;
    }

    private void checkMediaTypeBreaking(Set<RequestMediaTypeDeletedBreakingChange> breakingChanges, Path path, Request request, Request newRequest) {
        for (Map.Entry<MediaType, Schema> entry : request.getMediaTypes().entrySet()) {
            MediaType mediaType = entry.getKey();
            if (!newRequest.isMediaTypeAllowed(mediaType)) {
                breakingChanges.add(new RequestMediaTypeDeletedBreakingChange(path.getPath(), path.getMethod(), mediaType));
            }
        }
    }
}
