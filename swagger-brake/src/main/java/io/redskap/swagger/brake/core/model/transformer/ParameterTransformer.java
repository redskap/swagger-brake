package io.redskap.swagger.brake.core.model.transformer;

import io.redskap.swagger.brake.core.model.RequestParameter;
import io.redskap.swagger.brake.core.model.RequestParameterInType;
import io.redskap.swagger.brake.core.model.service.TypeRefNameResolver;
import io.redskap.swagger.brake.core.model.store.ParameterStore;
import io.redskap.swagger.brake.core.model.store.SchemaStore;
import io.redskap.swagger.brake.core.model.store.StoreProvider;
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
    private final TypeRefNameResolver typeRefNameResolver;

    @Override
    public RequestParameter transform(Parameter from) {
        if (from.get$ref() != null) {
            from = resolveRef(from);
        }
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

    private Parameter resolveRef(Parameter from) {
        ParameterStore parameterStore = StoreProvider.provideParameterStore();
        if (parameterStore == null) {
            throw new IllegalStateException("No ParameterStore available.");
        }
        String refName = typeRefNameResolver.resolve(from.get$ref());
        return parameterStore.get(refName).orElseThrow(() -> new IllegalStateException("Reference not found for " + refName));
    }


}
