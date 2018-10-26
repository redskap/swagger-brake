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
public class RequestParameterEnumValueDeletedRule  implements BreakingChangeRule<RequestParameterEnumValueDeletedBreakingChange> {
    @Override
    public Collection<RequestParameterEnumValueDeletedBreakingChange> checkRule(Specification oldApi, Specification newApi) {
        Set<RequestParameterEnumValueDeletedBreakingChange> breakingChanges = new HashSet<>();
        for (Path path : oldApi.getPaths()) {
            Optional<Path> newApiPath = newApi.getPath(path);
            if (newApiPath.isPresent()) {
                Path newPath = newApiPath.get();
                if (CollectionUtils.isNotEmpty(path.getRequestParameters())) {
                    for (RequestParameter requestParameter : path.getRequestParameters()) {
                        Optional<RequestParameter> newRequestParameter = newPath.getRequestParameterByName(requestParameter.getName());
                        if (newRequestParameter.isPresent()) {
                            RequestParameter newRequestParam = newRequestParameter.get();
                            Collection<String> oldEnumValues = requestParameter.getSchema().getEnumValues();
                            Collection<String> newEnumValues = newRequestParam.getSchema().getEnumValues();
                            for (String oldEnumValue : oldEnumValues) {
                                if (!newEnumValues.contains(oldEnumValue)) {
                                    breakingChanges.add(
                                        new RequestParameterEnumValueDeletedBreakingChange(path.getPath(), path.getMethod(),
                                            requestParameter.getName(), oldEnumValue));
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
