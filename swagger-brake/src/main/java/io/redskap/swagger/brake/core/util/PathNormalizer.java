package io.redskap.swagger.brake.core.util;

import org.apache.commons.lang3.StringUtils;

public abstract class PathNormalizer {
    public static String normalizePathSlashes(String path) {
        if (StringUtils.isBlank(path)) {
            throw new IllegalArgumentException("path cannot be blank");
        }
        String result = path;
        if (!result.startsWith("/")) {
            result = "/" + result;
        }
        if (result.endsWith("/")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
}
