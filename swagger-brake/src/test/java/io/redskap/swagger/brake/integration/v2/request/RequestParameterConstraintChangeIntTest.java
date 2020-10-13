package io.redskap.swagger.brake.integration.v2.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.HttpMethod;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraintChange;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraintChangedBreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RequestParameterConstraintChangeIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaximumChangesForInteger64() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/int64/maximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/int64/maximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maximum", new BigDecimal(10), new BigDecimal(5)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaximumChangesForInteger32() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/int32/maximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/int32/maximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maximum", new BigDecimal(10), new BigDecimal(5)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaximumChangesForIntegerBase() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/base/maximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/base/maximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maximum", new BigDecimal(10), new BigDecimal(5)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaximumIntroducedForInteger64() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/int64/newmaximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/int64/newmaximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maximum", null, new BigDecimal(10)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaximumIntroducedForInteger32() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/int32/newmaximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/int32/newmaximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maximum", null, new BigDecimal(10)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaximumIntroducedForIntegerBase() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/base/newmaximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/base/newmaximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maximum", null, new BigDecimal(10)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinimumChangesForInteger64() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/int64/minimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/int64/minimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minimum", new BigDecimal(1), new BigDecimal(3)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinimumChangesForInteger32() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/int32/minimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/int32/minimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minimum", new BigDecimal(1), new BigDecimal(3)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinimumChangesForIntegerBase() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/base/minimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/base/minimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minimum", new BigDecimal(1), new BigDecimal(3)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinimumIntroducedForInteger64() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/int64/newminimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/int64/newminimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minimum", null, new BigDecimal(3)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinimumIntroducedForInteger32() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/int32/newminimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/int32/newminimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minimum", null, new BigDecimal(3)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinimumIntroducedForIntegerBase() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/base/newminimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/base/newminimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minimum", null, new BigDecimal(3)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }
}
