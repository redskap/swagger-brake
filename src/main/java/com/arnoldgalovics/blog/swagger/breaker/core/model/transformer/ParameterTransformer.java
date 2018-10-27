package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import com.arnoldgalovics.blog.swagger.breaker.core.model.RequestParameter;
import com.arnoldgalovics.blog.swagger.breaker.core.model.RequestParameterInType;
import com.arnoldgalovics.blog.swagger.breaker.core.model.service.RequestParameterInTypeResolver;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import lombok.RequiredArgsConstructor;
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
        Schema swSchema = from.getSchema();
        if (swSchema != null) {
            return new RequestParameter(inType, name, schemaTransformer.transform(swSchema));
        }
        return new RequestParameter(inType, name);
    }
}
