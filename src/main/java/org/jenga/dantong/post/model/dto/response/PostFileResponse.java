package org.jenga.dantong.post.model.dto.response;

import java.util.Objects;
import lombok.Getter;
import org.jenga.dantong.post.model.entity.PostFile;
import org.springframework.http.MediaType;

@Getter
public class PostFileResponse {

    private final Long id;
    private final String url;
    private final String originalName;
    private final String mediaType;

    public PostFileResponse(PostFile file, String url) {
        this.id = file.getId();
        this.url = url;
        this.originalName = file.getFileName();
        this.mediaType = Objects.requireNonNullElse(file.getMediaType(),
            MediaType.APPLICATION_OCTET_STREAM_VALUE);
    }
}
