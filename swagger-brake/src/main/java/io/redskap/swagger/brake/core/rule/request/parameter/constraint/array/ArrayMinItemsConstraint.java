package io.redskap.swagger.brake.core.rule.request.parameter.constraint.array;

import java.util.Optional;

import io.redskap.swagger.brake.core.model.parameter.ArrayRequestParameter;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraint;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraintChange;
import org.springframework.stereotype.Component;

@Component
public class ArrayMinItemsConstraint implements RequestParameterConstraint<ArrayRequestParameter> {
    public static final String MIN_ITEMS_ATTRIBUTE_NAME = "minItems";

    @Override
    public Optional<RequestParameterConstraintChange> validateConstraints(ArrayRequestParameter oldRequestParameter, ArrayRequestParameter newRequestParameter) {
        RequestParameterConstraintChange result = null;
        if (oldRequestParameter != null && newRequestParameter != null) {
            Integer oldMinItems = oldRequestParameter.getMinItems();
            Integer newMinItems = newRequestParameter.getMinItems();
            if (oldMinItems == null && newMinItems != null) {
                result = new RequestParameterConstraintChange(
                    MIN_ITEMS_ATTRIBUTE_NAME, null, newMinItems
                );
            }
            if (oldMinItems != null && newMinItems != null) {
                if (oldMinItems < newMinItems) {
                    result = new RequestParameterConstraintChange(
                        MIN_ITEMS_ATTRIBUTE_NAME, oldMinItems, newMinItems
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
