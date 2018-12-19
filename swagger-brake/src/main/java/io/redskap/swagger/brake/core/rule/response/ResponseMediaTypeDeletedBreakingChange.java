package io.redskap.swagger.brake.core.rule.response;

import static java.lang.String.format;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.HttpMethod;
import io.redskap.swagger.brake.core.model.MediaType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class ResponseMediaTypeDeletedBreakingChange implements BreakingChange {
    private final String path;
    private final HttpMethod method;
    private final MediaType mediaType;

    @Override
    public String getMessage() {
        return format("%s media type request was removed from %s %s", mediaType, method, path);
    }
}
