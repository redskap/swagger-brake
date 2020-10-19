package io.redskap.swagger.brake.core.rule.request.parameter.constraint.array;

import java.util.Optional;

import io.redskap.swagger.brake.core.model.parameter.ArrayRequestParameter;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraint;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraintChange;
import org.springframework.stereotype.Component;

@Component
public class ArrayMaxItemsConstraint implements RequestParameterConstraint<ArrayRequestParameter> {
    public static final String MAX_ITEMS_ATTRIBUTE_NAME = "maxItems";

    @Override
    public Optional<RequestParameterConstraintChange> validateConstraints(ArrayRequestParameter oldRequestParameter, ArrayRequestParameter newRequestParameter) {
        RequestParameterConstraintChange result = null;
        if (oldRequestParameter != null && newRequestParameter != null) {
            Integer oldMaxItems = oldRequestParameter.getMaxItems();
            Integer newMaxItems = newRequestParameter.getMaxItems();
            if (oldMaxItems == null && newMaxItems != null) {
                result = new RequestParameterConstraintChange(
                    MAX_ITEMS_ATTRIBUTE_NAME, null, newMaxItems
                );
            }
            if (oldMaxItems != null && newMaxItems != null) {
                if (newMaxItems < oldMaxItems) {
                    result = new RequestParameterConstraintChange(
                        MAX_ITEMS_ATTRIBUTE_NAME, oldMaxItems, newMaxItems
                    );
                }
            }
        }
        return Optional.ofNullable(result);
    }

    @Override
    public Class<ArrayRequestParameter> handledRequestParameter() {
        return ArrayRequestParameter.class;
    }
}
