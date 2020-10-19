package io.redskap.swagger.brake.core.model.parameter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestParameterType {
    GENERIC(null, null),
    NUMBER("number", null),
    FLOAT("number", "float"),
    DOUBLE("number", "double"),
    INTEGER("integer", null),
    INT_32("integer", "int32"),
    INT_64("integer", "int64"),
    STRING("string", null),
    ARRAY("array", null);

    private String type;
    private String format;

    /**
     * Creates an instance of the enum based on the type and format arguments. If there's no corresponding enum value, it falls back to GENERIC.
     * @param type the type
     * @param format the format
     * @return the actual enum type or GENERIC if there's none
     */
    public static RequestParameterType from(String type, String format) {
        return forTypeAndFormat(type, format)
            .orElse(forTypeAndFormat(type, null).orElse(GENERIC));
    }

    private static Optional<RequestParameterType> forTypeAndFormat(String type, String format) {
        return Arrays.stream(RequestParameterType.values())
            .filter(e -> Objects.equals(e.getType(), type) && Objects.equals(e.getFormat(), format))
            .findAny();
    }

    /**
     * Returns all number types.
     * @return all number types
     */
    public static Collection<RequestParameterType> getNumberTypes() {
        return ImmutableList.of(NUMBER, FLOAT, DOUBLE, INTEGER, INT_32, INT_64);
    }

    /**
     * Returns all string types.
     * @return Returns all string types
     */
    public static Collection<RequestParameterType> getStringTypes() {
        return ImmutableList.of(STRING);
    }

    /**
     * Returns all array types.
     * @return Returns all array types
     */
    public static Collection<RequestParameterType> getArrayTypes() {
        return ImmutableList.of(ARRAY);
    }
}
