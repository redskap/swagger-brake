package io.redskap.swagger.brake.core.model;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Request {
    private final Map<MediaType, Schema> mediaTypes;

    public Optional<Schema> getSchemaByMediaType(MediaType mediaType) {
        return Optional.ofNullable(mediaTypes.get(mediaType));
    }

    public boolean isMediaTypeAllowed(MediaType mediaType) {
        Set<MediaType> availableMediaTypes = mediaTypes.keySet();
        if (availableMediaTypes.contains(MediaType.ALL)) {
            return true;
        }
        return availableMediaTypes.contains(mediaType);
    }
}
