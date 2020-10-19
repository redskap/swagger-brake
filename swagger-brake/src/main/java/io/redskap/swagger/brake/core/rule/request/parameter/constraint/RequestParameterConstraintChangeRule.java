package io.redskap.swagger.brake.core.rule.request.parameter.constraint;

import java.util.*;
import java.util.stream.Collectors;

import io.redskap.swagger.brake.core.model.Path;
import io.redskap.swagger.brake.core.model.Specification;
import io.redskap.swagger.brake.core.model.parameter.RequestParameter;
import io.redskap.swagger.brake.core.rule.BreakingChangeRule;
import io.redskap.swagger.brake.core.rule.PathSkipper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RequestParameterConstraintChangeRule implements BreakingChangeRule<RequestParameterConstraintChangedBreakingChange> {
    private final Collection<RequestParameterConstraint<?>> requestParameterConstraints;
    private final PathSkipper pathSkipper;

    @Override
    public Collection<RequestParameterConstraintChangedBreakingChange> checkRule(Specification oldApi, Specification newApi) {
        Set<RequestParameterConstraintChangedBreakingChange> breakingChanges = new HashSet<>();
        for (Path path : oldApi.getPaths()) {
            if (pathSkipper.shouldSkip(path)) {
                log.debug("Skipping {} as it's marked as a beta API", path);
                continue;
            }
            Optional<Path> newApiPath = newApi.getPath(path);
            if (newApiPath.isPresent()) {
                Path newPath = newApiPath.get();
                if (CollectionUtils.isNotEmpty(path.getRequestParameters())) {
                    for (RequestParameter requestParameter : path.getRequestParameters()) {
                        Optional<RequestParameter> newRequestParameter = newPath.getRequestParameterByName(requestParameter.getName());
                        if (newRequestParameter.isPresent()) {
                            RequestParameter newRequestParam = newRequestParameter.get();
                            breakingChanges.addAll(applyConstraints(path, requestParameter, newRequestParam));
                        }
                    }
                }
            }
        }
        return breakingChanges;
    }

    private <T extends RequestParameter> Collection<RequestParameterConstraintChangedBreakingChange> applyConstraints(Path path, T requestParameter, T newRequestParameter) {
        Class<T> classType = (Class<T>) requestParameter.getClass();
        Class<T> newClassType = (Class<T>) newRequestParameter.getClass();
        List<RequestParameterConstraintChangedBreakingChange> bcs = requestParameterConstraints.stream()
            .filter(c -> c.handledRequestParameter().equals(classType))
            .filter(c -> c.handledRequestParameter().equals(newClassType))
            .map(c -> ((RequestParameterConstraint<T>) c).validateConstraints(requestParameter, newRequestParameter))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(cc -> new RequestParameterConstraintChangedBreakingChange(path.getPath(), path.getMethod(), requestParameter.getName(), cc))
            .collect(Collectors.toList());
        return bcs;
    }
}
