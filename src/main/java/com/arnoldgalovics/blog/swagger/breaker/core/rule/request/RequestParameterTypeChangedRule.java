package com.arnoldgalovics.blog.swagger.breaker.core.rule.request;

import java.util.*;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Path;
import com.arnoldgalovics.blog.swagger.breaker.core.model.RequestParameter;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Schema;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Specification;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.BreakingChangeRule;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class RequestParameterTypeChangedRule implements BreakingChangeRule<RequestParameterTypeChangedBreakingChange> {
    @Override
    public Collection<RequestParameterTypeChangedBreakingChange> checkRule(Specification oldApi, Specification newApi) {
        Set<RequestParameterTypeChangedBreakingChange> breakingChanges = new HashSet<>();
        for (Path path : oldApi.getPaths()) {
            Optional<Path> newApiPath = newApi.getPath(path);
            if (newApiPath.isPresent()) {
                Path newPath = newApiPath.get();
                if (CollectionUtils.isNotEmpty(path.getRequestParameters())) {
                    for (RequestParameter requestParameter : path.getRequestParameters()) {
                        Optional<RequestParameter> newRequestParameter = newPath.getRequestParameterByName(requestParameter.getName());
                        if (newRequestParameter.isPresent()) {
                            RequestParameter newRequestParam = newRequestParameter.get();
                            if (requestParameter.getSchema().isPresent()) {
                                if (newRequestParam.getSchema().isPresent()) {
                                    Schema schema = requestParameter.getSchema().get();
                                    Schema newSchema = newRequestParam.getSchema().get();
                                    Map<String, String> newTypes = newSchema.getTypes();
                                    for (Map.Entry<String, String> type : schema.getTypes().entrySet()) {
                                        String attribute = type.getKey();
                                        String typeName = type.getValue();
                                        String newType = newTypes.get(attribute);
                                        if (!StringUtils.equals(typeName, newType)) {
                                            breakingChanges.add(
                                                new RequestParameterTypeChangedBreakingChange(path.getPath(), path.getMethod(),
                                                    requestParameter.getName(), attribute, typeName, newType));
                                        }
                                    }
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
