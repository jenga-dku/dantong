package org.jenga.dantong.global.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.jenga.dantong.global.s3.exception.EmptyFileException;
import org.jenga.dantong.global.s3.exception.InvalidateExtensionException;
import org.jenga.dantong.global.s3.exception.NoExtensionException;
import org.jenga.dantong.global.s3.model.dto.FileUploadRequest;
import org.jenga.dantong.global.s3.model.dto.RequestFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;


    public ArrayList<RequestFile> uploadFiles(List<FileUploadRequest> files) {
        ArrayList<RequestFile> requestFiles = new ArrayList<>();
        for (FileUploadRequest file :
            files) {
            requestFiles.add(upload(file));
        }
        return requestFiles;
    }

    public RequestFile upload(FileUploadRequest file) {
        if (Objects.isNull(file.getOriginalFilename())) {
            throw new EmptyFileException();
        }

        return this.uploadImage(file);
    }

    public RequestFile uploadImage(FileUploadRequest image) {
        this.validateImageFileExtension(image.getOriginalFilename());
        try {
            return this.uploadImageToS3(image);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void validateImageFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex == -1) {
            throw new NoExtensionException();
        }

        String extension = filename.substring(lastDotIndex + 1).toLowerCase();
        List<String> allowedExtentionList = Arrays.asList("jpg", "jpeg", "png", "gif");

        if (!allowedExtentionList.contains(extension)) {
            throw new InvalidateExtensionException();
        }
    }

    public RequestFile uploadImageToS3(FileUploadRequest image) throws IOException {
        String originalFilename = image.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        String s3FileName =
            UUID.randomUUID().toString().substring(0, 10) + originalFilename;

        InputStream is = image.getInputStream();
        byte[] bytes = IOUtils.toByteArray(is);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/" + extension);
        metadata.setContentLength(bytes.length);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        try {
            PutObjectRequest putObjectRequest =
                new PutObjectRequest(bucketName, s3FileName, byteArrayInputStream, metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            byteArrayInputStream.close();
            is.close();
        }

        return new RequestFile(s3FileName, image);
    }

    public void deleteImageFromS3(String imageAddress) throws IOException {
        String key = getKeyFromImageAddress(imageAddress);
        try {
            amazonS3.deleteObject(new DeleteObjectRequest(bucketName, key));
        } catch (Exception e) {
            throw new IOException();
        }
    }

    private String getKeyFromImageAddress(String imageAddress) throws IOException {
        try {
            URL url = new URL(imageAddress);
            String decodingKey = URLDecoder.decode(url.getPath(), "UTF-8");
            return decodingKey.substring(1);
        } catch (MalformedURLException | UnsupportedEncodingException e) {
            throw new IOException();
        }
    }

    public String getFileUrl(String fileName) {
        return amazonS3.getUrl(bucketName, fileName).toString();
    }
}
