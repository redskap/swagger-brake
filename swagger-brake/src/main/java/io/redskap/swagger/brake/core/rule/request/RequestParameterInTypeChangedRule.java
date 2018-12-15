package io.redskap.swagger.brake.core.rule.request;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import io.redskap.swagger.brake.core.model.Path;
import io.redskap.swagger.brake.core.model.RequestParameter;
import io.redskap.swagger.brake.core.model.Specification;
import io.redskap.swagger.brake.core.rule.BreakingChangeRule;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

@Component
public class RequestParameterInTypeChangedRule implements BreakingChangeRule<RequestParameterInTypeChangedBreakingChange> {
    @Override
    public Collection<RequestParameterInTypeChangedBreakingChange> checkRule(Specification oldApi, Specification newApi) {
        Set<RequestParameterInTypeChangedBreakingChange> breakingChanges = new HashSet<>();
        for (Path path : oldApi.getPaths()) {
            Optional<Path> newApiPath = newApi.getPath(path);
            if (newApiPath.isPresent()) {
                Path newPath = newApiPath.get();
                if (CollectionUtils.isNotEmpty(path.getRequestParameters())) {
                    for (RequestParameter requestParameter : path.getRequestParameters()) {
                        Optional<RequestParameter> newRequestParameter = newPath.getRequestParameterByName(requestParameter.getName());
                        if (newRequestParameter.isPresent()) {
                            RequestParameter newRequestParam = newRequestParameter.get();
                            if (!requestParameter.getInType().equals(newRequestParam.getInType())) {
                                breakingChanges.add(
                                    new RequestParameterInTypeChangedBreakingChange(path.getPath(), path.getMethod(),
                                        requestParameter.getName(), requestParameter.getInType().getName(), newRequestParam.getInType().getName()));
                            }
                        }
                    }
                }
            }
        }
        return breakingChanges;
    }
}
