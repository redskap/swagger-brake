package io.redskap.swagger.brake.core.rule.response;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import io.redskap.swagger.brake.core.model.Path;
import io.redskap.swagger.brake.core.model.Response;
import io.redskap.swagger.brake.core.model.Specification;
import io.redskap.swagger.brake.core.rule.BreakingChangeRule;
import org.springframework.stereotype.Component;

@Component
public class ResponseDeletedRule implements BreakingChangeRule<ResponseDeletedBreakingChange> {
    @Override
    public Collection<ResponseDeletedBreakingChange> checkRule(Specification oldApi, Specification newApi) {
        Set<ResponseDeletedBreakingChange> breakingChanges = new HashSet<>();
        for (Path path : oldApi.getPaths()) {
            Optional<Path> newApiPath = newApi.getPath(path);
            if (newApiPath.isPresent()) {
                Path newPath = newApiPath.get();
                for (Response apiResponse : path.getResponses()) {
                    Optional<Response> newApiResponse = newPath.getResponseByCode(apiResponse.getCode());
                    if (!newApiResponse.isPresent()) {
                        breakingChanges.add(new ResponseDeletedBreakingChange(path.getPath(), path.getMethod(), apiResponse.getCode()));
                    }
                }
            }
        }
        return breakingChanges;
    }
}
