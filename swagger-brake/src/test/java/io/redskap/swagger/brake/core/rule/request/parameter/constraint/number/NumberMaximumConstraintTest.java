package io.redskap.swagger.brake.core.rule.request.parameter.constraint.number;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import io.redskap.swagger.brake.core.model.RequestParameterInType;
import io.redskap.swagger.brake.core.model.parameter.NumberRequestParameter;
import io.redskap.swagger.brake.core.model.parameter.RequestParameterType;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraintChange;
import org.junit.Test;

public class NumberMaximumConstraintTest {
    private NumberMaximumConstraint underTest = new NumberMaximumConstraint();

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenNullOldRequestParamIsGiven() {
        // given
        NumberRequestParameter oldRequestParameter = null;
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.ONE,
            BigDecimal.ZERO,
            true,
            true
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenNullNewRequestParamIsGiven() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.ONE,
            BigDecimal.ZERO,
            true,
            true
        );
        NumberRequestParameter newRequestParameter = null;
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenRequestParameterIsNotIntegerTyped() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.GENERIC,
            BigDecimal.TEN,
            BigDecimal.ZERO,
            true,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.GENERIC,
            BigDecimal.ONE,
            BigDecimal.ZERO,
            true,
            true
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenMaximumValueSetIsExtended() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.ONE,
            BigDecimal.ZERO,
            true,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN,
            BigDecimal.ZERO,
            true,
            true
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenMaximumValueSetIsExtendedWithExclusiveMaximumSetting() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN,
            BigDecimal.ZERO,
            true,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN,
            BigDecimal.ZERO,
            false,
            true
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMaximumValueSetIsLimitedWithExclusiveMaximumSetting() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN,
            BigDecimal.ZERO,
            false,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN,
            BigDecimal.ZERO,
            true,
            true
        );
        RequestParameterConstraintChange expected = new RequestParameterConstraintChange(
            "exclusiveMaximum", false, true
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMaximumValueSetIsLimitedWithExclusiveMaximumSettingEdgeCase1() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN.add(BigDecimal.ONE),
            BigDecimal.ZERO,
            true,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN,
            BigDecimal.ZERO,
            false,
            true
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMaximumValueSetIsLimitedWithExclusiveMaximumSettingEdgeCase2() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN,
            BigDecimal.ZERO,
            false,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN.add(BigDecimal.ONE),
            BigDecimal.ZERO,
            true,
            true
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMaximumValueSetGetsLimitedForInt64() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN,
            BigDecimal.ZERO,
            false,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.ONE,
            BigDecimal.ZERO,
            false,
            true
        );
        RequestParameterConstraintChange expected = new RequestParameterConstraintChange(
            "maximum", BigDecimal.TEN, BigDecimal.ONE
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMaximumValueSetGetsLimitedForInt32() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_32,
            BigDecimal.TEN,
            BigDecimal.ZERO,
            false,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_32,
            BigDecimal.ONE,
            BigDecimal.ZERO,
            false,
            true
        );
        RequestParameterConstraintChange expected = new RequestParameterConstraintChange(
            "maximum", BigDecimal.TEN, BigDecimal.ONE
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMaximumValueSetGetsLimitedForInteger() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INTEGER,
            BigDecimal.TEN,
            BigDecimal.ZERO,
            false,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INTEGER,
            BigDecimal.ONE,
            BigDecimal.ZERO,
            false,
            true
        );
        RequestParameterConstraintChange expected = new RequestParameterConstraintChange(
            "maximum", BigDecimal.TEN, BigDecimal.ONE
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMaximumValueGetsSet() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INTEGER,
            null,
            BigDecimal.ZERO,
            false,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INTEGER,
            BigDecimal.ONE,
            BigDecimal.ZERO,
            false,
            true
        );
        RequestParameterConstraintChange expected = new RequestParameterConstraintChange(
            "maximum", null, BigDecimal.ONE
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMaximumValueGetsRemoved() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INTEGER,
            BigDecimal.ONE,
            BigDecimal.ZERO,
            false,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INTEGER,
            null,
            BigDecimal.ZERO,
            false,
            true
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }
}