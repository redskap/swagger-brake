package io.redskap.swagger.brake.core.rule.request.parameter.constraint.array;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import io.redskap.swagger.brake.core.model.RequestParameterInType;
import io.redskap.swagger.brake.core.model.parameter.ArrayRequestParameter;
import io.redskap.swagger.brake.core.model.parameter.RequestParameterType;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraintChange;
import org.junit.Test;

public class ArrayMinItemsConstraintTest {
    private ArrayMinItemsConstraint underTest = new ArrayMinItemsConstraint();

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenNullOldRequestParamIsGiven() {
        // given
        ArrayRequestParameter oldRequestParameter = null;
        ArrayRequestParameter newRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            1,
            1,
            false
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenNullNewRequestParamIsGiven() {
        // given
        ArrayRequestParameter oldRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            1,
            1,
            false
        );
        ArrayRequestParameter newRequestParameter = null;
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenRequestParameterIsNotStringTyped() {
        // given
        ArrayRequestParameter oldRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.GENERIC,
            1,
            1,
            false
        );
        ArrayRequestParameter newRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.GENERIC,
            1,
            1,
            false
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenMinLengthIsExtended() {
        // given
        ArrayRequestParameter oldRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            1,
            1,
            false
        );
        ArrayRequestParameter newRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            1,
            0,
            false
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenMinLengthIsRemoved() {
        // given
        ArrayRequestParameter oldRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            1,
            1,
            false
        );
        ArrayRequestParameter newRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            1,
            null,
            false
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMinLengthGetsLimited() {
        // given
        ArrayRequestParameter oldRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            5,
            1,
            false
        );
        ArrayRequestParameter newRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            5,
            2,
            false
        );
        RequestParameterConstraintChange expected = new RequestParameterConstraintChange(
            "minItems", 1, 2
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMinLengthGetsSet() {
        // given
        ArrayRequestParameter oldRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            1,
            null,
            false
        );
        ArrayRequestParameter newRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            1,
            1,
            false
        );
        RequestParameterConstraintChange expected = new RequestParameterConstraintChange(
            "minItems", null, 1
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }
}