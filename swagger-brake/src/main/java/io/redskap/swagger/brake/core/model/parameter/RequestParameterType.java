package io.redskap.swagger.brake.core.model.parameter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestParameterType {
    GENERIC(null, null),
    INTEGER("integer", null),
    INT_32("integer", "int32"),
    INT_64("integer", "int64");

    private String type;
    private String format;

    /**
     * Creates an instance of the enum based on the type and format arguments. If there's no corresponding enum value, it falls back to GENERIC.
     * @param type the type
     * @param format the format
     * @return the actual enum type or GENERIC if there's none
     */
    public static RequestParameterType from(String type, String format) {
        return Arrays.stream(RequestParameterType.values())
            .filter(e -> Objects.equals(e.getType(), type) && Objects.equals(e.getFormat(), format))
            .findAny()
            .orElse(GENERIC);
    }

    /**
     * Returns all integer types.
     * @return all integer types
     */
    public static Collection<RequestParameterType> getIntegerTypes() {
        return ImmutableList.of(INTEGER, INT_32, INT_64);
    }
}
