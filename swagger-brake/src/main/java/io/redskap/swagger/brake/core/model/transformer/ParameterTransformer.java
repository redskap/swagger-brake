package io.redskap.swagger.brake.core.model.transformer;

import io.redskap.swagger.brake.core.model.RequestParameter;
import io.redskap.swagger.brake.core.model.RequestParameterInType;
import io.redskap.swagger.brake.core.model.service.RequestParameterInTypeResolver;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParameterTransformer implements Transformer<Parameter, RequestParameter> {
    private final SchemaTransformer schemaTransformer;
    private final RequestParameterInTypeResolver requestParameterInTypeResolver;

    @Override
    public RequestParameter transform(Parameter from) {
        RequestParameterInType inType = requestParameterInTypeResolver.resolve(from.getIn());
        String name = from.getName();
        boolean required = BooleanUtils.toBoolean(from.getRequired());
        Schema swSchema = from.getSchema();
        if (swSchema != null) {
            // Validation for detecting when a request parameter is used with an actual schema object
            // even though its forbidden by the spec
            // https://github.com/redskap/swagger-brake/issues/28
            if (swSchema.getType() == null) {
                throw new IllegalStateException("schema does not have any type");
            }
            return new RequestParameter(inType, name, required, schemaTransformer.transform(swSchema));
        }
        return new RequestParameter(inType, name, required);
    }
}
