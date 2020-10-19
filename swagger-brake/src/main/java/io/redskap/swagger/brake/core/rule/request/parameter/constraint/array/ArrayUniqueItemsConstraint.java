package io.redskap.swagger.brake.core.rule.request.parameter.constraint.array;

import java.util.Optional;

import io.redskap.swagger.brake.core.model.parameter.ArrayRequestParameter;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraint;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraintChange;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;

@Component
public class ArrayUniqueItemsConstraint implements RequestParameterConstraint<ArrayRequestParameter> {
    public static final String UNIQUE_ITEMS_ATTRIBUTE_NAME = "uniqueItems";

    @Override
    public Optional<RequestParameterConstraintChange> validateConstraints(ArrayRequestParameter oldRequestParameter, ArrayRequestParameter newRequestParameter) {
        RequestParameterConstraintChange result = null;
        if (oldRequestParameter != null && newRequestParameter != null) {
            Boolean oldUniqueItems = oldRequestParameter.getUniqueItems();
            Boolean newUniqueItems = newRequestParameter.getUniqueItems();
            if (BooleanUtils.isTrue(newUniqueItems) && BooleanUtils.isNotTrue(oldUniqueItems)) {
                result = new RequestParameterConstraintChange(
                  UNIQUE_ITEMS_ATTRIBUTE_NAME,
                  oldUniqueItems,
                  newUniqueItems
                );
            }
        }
        return Optional.ofNullable(result);
    }

    @Override
    public Class<ArrayRequestParameter> handledRequestParameter() {
        return ArrayRequestParameter.class;
    }
}
