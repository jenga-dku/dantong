package org.jenga.dantong.global.s3.model.dto;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jenga.dantong.global.s3.exception.InvalidFileException;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@Getter
@RequiredArgsConstructor
public class FileUploadRequest {

    private final String originalFilename;
    private final MediaType contentType;
    private final InputStreamSupplier inStreamSupplier;


    public FileUploadRequest(MultipartFile file) {
        this.originalFilename = file.getOriginalFilename();
        this.inStreamSupplier = file::getInputStream;

        String fileMimeType = file.getContentType();
        if (fileMimeType == null) {
            this.contentType = MediaType.APPLICATION_OCTET_STREAM;
        } else {
            try {
                this.contentType = MediaType.parseMediaType(file.getContentType());
            } catch (InvalidMediaTypeException e) {
                throw new InvalidFileException();
            }
        }
    }

    public InputStream getInputStream() throws IOException {
        return inStreamSupplier.get();
    }

    public static List<FileUploadRequest> ofList(List<MultipartFile> files) {
        List<FileUploadRequest> fileRequests = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                fileRequests.add(new FileUploadRequest(file));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return fileRequests;
    }

    @FunctionalInterface
    public interface InputStreamSupplier {

        InputStream get() throws IOException;
    }
}
