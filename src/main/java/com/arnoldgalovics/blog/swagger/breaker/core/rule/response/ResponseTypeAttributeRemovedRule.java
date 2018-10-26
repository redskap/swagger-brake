package com.arnoldgalovics.blog.swagger.breaker.core.rule.response;

import static java.util.Collections.emptyList;

import java.util.*;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.model.*;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.BreakingChangeRule;
import org.springframework.stereotype.Component;

@Component
public class ResponseTypeAttributeRemovedRule implements BreakingChangeRule {
    @Override
    public Collection<? extends BreakingChange> checkRule(Specification oldApi, Specification newApi) {
        Set<ResponseTypeAttributeRemovedBreakingChange> breakingChanges = new HashSet<>();
        for (Path path : oldApi.getPaths()) {
            Optional<Path> newApiPath = newApi.getPath(path);
            if (newApiPath.isPresent()) {
                Path newPath = newApiPath.get();
                for (Response apiResponse : path.getResponses()) {
                    Optional<Response> newApiResponse = newPath.getResponseByCode(apiResponse.getCode());
                    if (newApiResponse.isPresent()) {
                        Response newResponse = newApiResponse.get();
                        for (SchemaRef schemaRef : apiResponse.getSchemaRefs()) {
                            Optional<SchemaRef> newApiSchemaRef = newResponse.getSchemaRefByMediaType(schemaRef.getMediaType());
                            if (newApiSchemaRef.isPresent()) {
                                SchemaRef newSchemaRef = newApiSchemaRef.get();
                                Collection<String> oldAttributeNames = getAttributeNamesFromRef(oldApi, schemaRef);
                                Collection<String> newAttributeNames = getAttributeNamesFromRef(newApi, newSchemaRef);
                                for (String oldAttributeName : oldAttributeNames) {
                                    if (!newAttributeNames.contains(oldAttributeName)) {
                                        breakingChanges.add(new ResponseTypeAttributeRemovedBreakingChange(schemaRef.getRefName(), oldAttributeName));
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

    private Collection<String> getAttributeNamesFromRef(Specification apiSpec, SchemaRef schemaRef) {
        return apiSpec.getSchemaStore().get(schemaRef.getRefName()).map(Schema::getAttributeNames).orElse(emptyList());
    }
}
