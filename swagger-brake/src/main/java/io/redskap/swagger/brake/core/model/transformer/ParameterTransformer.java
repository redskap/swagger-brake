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
            return new RequestParameter(inType, name, required, schemaTransformer.transform(swSchema));
        }
        return new RequestParameter(inType, name, required);
    }
}
