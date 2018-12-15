package io.redskap.swagger.brake.core.model;

import java.util.Map;
import java.util.Optional;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Request {
    private final Map<String, Schema> mediaTypes;

    public Optional<Schema> getSchemaByMediaType(String mediaType) {
        return Optional.ofNullable(mediaTypes.get(mediaType));
    }
}
