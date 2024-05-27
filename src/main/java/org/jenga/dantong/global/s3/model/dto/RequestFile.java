package org.jenga.dantong.global.s3.model.dto;

import lombok.Getter;
import org.springframework.http.MediaType;

@Getter
public class RequestFile {

    private final String fileId;
    private final String originalName;
    private final MediaType mediaType;
    private final FileUploadRequest file;

    public RequestFile(String fileId, FileUploadRequest request) {
        this.fileId = fileId;
        this.originalName = request.getOriginalFilename();
        this.mediaType = request.getContentType();
        this.file = request;
    }
}
