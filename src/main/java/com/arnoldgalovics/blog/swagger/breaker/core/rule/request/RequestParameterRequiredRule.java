package com.arnoldgalovics.blog.swagger.breaker.core.rule.request;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Path;
import com.arnoldgalovics.blog.swagger.breaker.core.model.RequestParameter;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Specification;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.BreakingChangeRule;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

@Component
public class RequestParameterRequiredRule implements BreakingChangeRule<RequestParameterRequiredBreakingChange> {
    @Override
    public Collection<RequestParameterRequiredBreakingChange> checkRule(Specification oldApi, Specification newApi) {
        Set<RequestParameterRequiredBreakingChange> breakingChanges = new HashSet<>();
        for (Path path : oldApi.getPaths()) {
            Optional<Path> newApiPath = newApi.getPath(path);
            if (newApiPath.isPresent()) {
                Path newPath = newApiPath.get();
                if (CollectionUtils.isNotEmpty(path.getRequestParameters())) {
                    breakingChanges.addAll(checkOldNonRequiredParameters(path, newPath));
                }
                if (CollectionUtils.isNotEmpty(newPath.getRequestParameters())) {
                    breakingChanges.addAll(checkNewRequiredParameters(path, newPath));
                }
            }
        }
        return breakingChanges;
    }

    private Collection<RequestParameterRequiredBreakingChange> checkNewRequiredParameters(Path path, Path newPath) {
        Set<RequestParameterRequiredBreakingChange> breakingChanges = new HashSet<>();
        for (RequestParameter newRequestParameter : newPath.getRequestParameters()) {
            Optional<RequestParameter> oldRequestParameter = path.getRequestParameterByName(newRequestParameter.getName());
            if (!oldRequestParameter.isPresent()) {
                if (newRequestParameter.isRequired()) {
                    breakingChanges.add(new RequestParameterRequiredBreakingChange(path.getPath(), path.getMethod(), newRequestParameter.getName()));
                }
            }
        }
        return breakingChanges;
    }

    private Collection<RequestParameterRequiredBreakingChange> checkOldNonRequiredParameters(Path path, Path newPath) {
        Set<RequestParameterRequiredBreakingChange> breakingChanges = new HashSet<>();
        for (RequestParameter requestParameter : path.getRequestParameters()) {
            Optional<RequestParameter> newRequestParam = newPath.getRequestParameterByName(requestParameter.getName());
            if (newRequestParam.isPresent()) {
                RequestParameter newRequestParameter = newRequestParam.get();
                if (!requestParameter.isRequired() && newRequestParameter.isRequired()) {
                    breakingChanges.add(new RequestParameterRequiredBreakingChange(path.getPath(), path.getMethod(), requestParameter.getName()));
                }
            }
        }
        return breakingChanges;
    }
}
