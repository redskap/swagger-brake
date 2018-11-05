package io.redskap.swagger.brake.core.rule.response;

import java.util.*;

import io.redskap.swagger.brake.core.model.Path;
import io.redskap.swagger.brake.core.model.Response;
import io.redskap.swagger.brake.core.model.Schema;
import io.redskap.swagger.brake.core.model.Specification;
import io.redskap.swagger.brake.core.rule.BreakingChangeRule;
import org.springframework.stereotype.Component;

@Component
public class ResponseTypeEnumValueDeletedRule implements BreakingChangeRule<ResponseTypeEnumValueDeletedBreakingChange> {
    @Override
    public Collection<ResponseTypeEnumValueDeletedBreakingChange> checkRule(Specification oldApi, Specification newApi) {
        Set<ResponseTypeEnumValueDeletedBreakingChange> breakingChanges = new HashSet<>();
        for (Path path : oldApi.getPaths()) {
            Optional<Path> newApiPath = newApi.getPath(path);
            if (newApiPath.isPresent()) {
                Path newPath = newApiPath.get();
                for (Response apiResponse : path.getResponses()) {
                    Optional<Response> newApiResponse = newPath.getResponseByCode(apiResponse.getCode());
                    if (newApiResponse.isPresent()) {
                        Response newResponse = newApiResponse.get();
                        for (Map.Entry<String, Schema> entry : apiResponse.getMediaTypes().entrySet()) {
                            String mediaType = entry.getKey();
                            Schema schema = entry.getValue();
                            Optional<Schema> newApiSchema = newResponse.getSchemaByMediaType(mediaType);
                            if (newApiSchema.isPresent()) {
                                Collection<String> oldEnumValues = schema.getEnums();
                                Collection<String> newEnumValues = newApiSchema.get().getEnums();
                                for (String oldEnumValue : oldEnumValues) {
                                    if (!newEnumValues.contains(oldEnumValue)) {
                                        breakingChanges.add(
                                            new ResponseTypeEnumValueDeletedBreakingChange(path.getPath(), path.getMethod(), oldEnumValue));
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
