package io.redskap.swagger.brake.util;

import java.util.Optional;

public abstract class ExceptionUtils {
    public static <T extends Throwable> Optional<T> findInChain(Throwable throwable, Class<T> type) {
        return org.apache.commons.lang3.exception.ExceptionUtils.getThrowableList(throwable)
            .stream()
            .filter(type::isInstance)
            .findFirst().map(type::cast);
    }
}
