package io.redskap.swagger.brake.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

@Getter
@EqualsAndHashCode
@ToString
public class MediaType {
    public static final MediaType ALL = new MediaType("*/*");

    private final MimeType mediaType;

    public MediaType(String mediaType) {
        this.mediaType = MimeTypeUtils.parseMimeType(mediaType);
    }
}
