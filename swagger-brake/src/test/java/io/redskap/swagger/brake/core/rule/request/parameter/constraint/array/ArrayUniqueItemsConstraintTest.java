package io.redskap.swagger.brake.core.rule.request.parameter.constraint.array;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import io.redskap.swagger.brake.core.model.RequestParameterInType;
import io.redskap.swagger.brake.core.model.parameter.ArrayRequestParameter;
import io.redskap.swagger.brake.core.model.parameter.RequestParameterType;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraintChange;
import org.junit.Test;

public class ArrayUniqueItemsConstraintTest {
    private ArrayUniqueItemsConstraint underTest = new ArrayUniqueItemsConstraint();

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenUniqueItemsGetsSet() {
        // given
        ArrayRequestParameter oldRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            2,
            1,
            null
        );
        ArrayRequestParameter newRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            2,
            1,
            true
        );
        RequestParameterConstraintChange expected = new RequestParameterConstraintChange(
            "uniqueItems", null, true
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenUniqueItemsGetsChangedToTrue() {
        // given
        ArrayRequestParameter oldRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            2,
            1,
            false
        );
        ArrayRequestParameter newRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            2,
            1,
            true
        );
        RequestParameterConstraintChange expected = new RequestParameterConstraintChange(
            "uniqueItems", false, true
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenUniqueItemsGetsSetToFalse() {
        // given
        ArrayRequestParameter oldRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            2,
            1,
            null
        );
        ArrayRequestParameter newRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            2,
            1,
            false
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenUniqueItemsGetsChangedToFalse() {
        // given
        ArrayRequestParameter oldRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            2,
            1,
            true
        );
        ArrayRequestParameter newRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            2,
            1,
            false
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }
}