package io.redskap.swagger.brake.core.rule.response;

import java.util.*;

import io.redskap.swagger.brake.core.model.*;
import io.redskap.swagger.brake.core.rule.BreakingChangeRule;
import org.springframework.stereotype.Component;

@Component
public class ResponseMediaTypeDeletedRule implements BreakingChangeRule<ResponseMediaTypeDeletedBreakingChange> {
    @Override
    public Collection<ResponseMediaTypeDeletedBreakingChange> checkRule(Specification oldApi, Specification newApi) {
        Set<ResponseMediaTypeDeletedBreakingChange> breakingChanges = new HashSet<>();
        for (Path path : oldApi.getPaths()) {
            Optional<Path> newApiPath = newApi.getPath(path);
            if (newApiPath.isPresent()) {
                Path newPath = newApiPath.get();
                for (Response apiResponse : path.getResponses()) {
                    Optional<Response> newApiResponse = newPath.getResponseByCode(apiResponse.getCode());
                    if (newApiResponse.isPresent()) {
                        Response newResponse = newApiResponse.get();
                        checkMediaTypeBreaking(breakingChanges, path, apiResponse, newResponse);
                    }
                }
            }
        }
        return breakingChanges;
    }

    private void checkMediaTypeBreaking(Set<ResponseMediaTypeDeletedBreakingChange> breakingChanges, Path path, Response apiResponse, Response newResponse) {
        for (Map.Entry<MediaType, Schema> entry : apiResponse.getMediaTypes().entrySet()) {
            MediaType mediaType = entry.getKey();
            if (!newResponse.isMediaTypeAllowed(mediaType)) {
                breakingChanges.add(new ResponseMediaTypeDeletedBreakingChange(path.getPath(), path.getMethod(), mediaType));
            }
        }
    }
}
