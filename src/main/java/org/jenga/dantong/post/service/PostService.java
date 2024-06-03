package org.jenga.dantong.post.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.global.s3.model.dto.FileUploadRequest;
import org.jenga.dantong.global.s3.model.dto.RequestFile;
import org.jenga.dantong.global.s3.service.FileUploadService;
import org.jenga.dantong.global.util.Util;
import org.jenga.dantong.post.exception.PermissionDeniedException;
import org.jenga.dantong.post.exception.PostNofFoundException;
import org.jenga.dantong.post.model.dto.PostCreateRequest;
import org.jenga.dantong.post.model.dto.PostFileResponse;
import org.jenga.dantong.post.model.dto.PostResponse;
import org.jenga.dantong.post.model.dto.PostUpdateRequest;
import org.jenga.dantong.post.model.entity.Category;
import org.jenga.dantong.post.model.entity.Post;
import org.jenga.dantong.post.model.entity.PostFile;
import org.jenga.dantong.post.repository.PostRepository;
import org.jenga.dantong.user.exception.UserNotFoundException;
import org.jenga.dantong.user.model.entity.User;
import org.jenga.dantong.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;

    @Transactional
    public Long savePost(PostCreateRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Post post = Post.builder()
                .user(user)
                .title(request.getTitle())
                .description(request.getDescription())
                .content(request.getContent())
                .category(request.getCategory())
                .startDate(request.getStartTime())
                .endDate(request.getEndTime())
                .shown(request.isShown())
                .build();
        log.info(request.getTitle());
        if (request.getImageFiles() != null) {
            saveFiles(request.getImageFiles(), post);
        }
        Post savedPost = postRepository.save(post);
        return savedPost.getPostId();
    }

    @Transactional
    public PostResponse findPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNofFoundException::new);
        String progress = Util.getProgress(post);
        List<PostFileResponse> files = post.getFiles().stream()
                .map(file -> {
                    String url = fileUploadService.getFileUrl(file.getFileId());
                    return new PostFileResponse(file, url);
                }).collect(Collectors.toList());
        return new PostResponse(post, progress, files);
    }

    @Transactional
    public Long deletePost(Long postId, Long userId) throws PermissionDeniedException {
        Post post = postRepository.findById(postId).orElseThrow(PostNofFoundException::new);

        if (userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new).getId() == post.getUser().getId()) {

            post.setShown(false);
            postRepository.save(post);

            return postId;
        } else {
            throw new PermissionDeniedException();
        }
    }

    @Transactional
    public Page<PostResponse> showAllPost(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        return getPostResponses(posts);
    }

    @Transactional
    public Page<PostResponse> showByCategory(Category category, Pageable pageable) {
        Page<Post> posts = postRepository.findByCategory(category, pageable);
        return getPostResponses(posts);
    }

    private Page<PostResponse> getPostResponses(Page<Post> posts) {
        return posts.map(currPost -> {
            List<PostFileResponse> files = currPost.getFiles().stream()
                    .map(file -> {
                        String url = fileUploadService.getFileUrl(file.getFileId());
                        return new PostFileResponse(file, url);
                    }).collect(Collectors.toList());
            String progress = Util.getProgress(currPost);
            return new PostResponse(currPost, progress, files);
        });
    }

    @Transactional
    public Long updatePost(Long postId, PostUpdateRequest request, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNofFoundException::new);
        if (!userId.equals(post.getUser().getId())) {
            throw new PermissionDeniedException();
        }
        post.setContent(request.getContent());
        post.setTitle(request.getTitle());
        post.setDescription(request.getDescription());
        post.setCategory(request.getCategory());
        post.setStartDate(request.getStart_time());
        post.setEndDate(request.getEnd_time());

        return post.getPostId();
    }

    private void saveFiles(List<MultipartFile> files, Post post) {
        List<RequestFile> requestFiles = fileUploadService.uploadFiles(
                FileUploadRequest.ofList(files));
        List<PostFile> postFiles = new ArrayList<>();

        for (RequestFile file : requestFiles) {
            PostFile.PostFileBuilder builder = PostFile.builder()
                    .fileName(file.getOriginalName())
                    .mediaType(file.getMediaType().toString())
                    .fileId(file.getFileId());

            postFiles.add(builder.build());
        }

        for (PostFile file : postFiles) {
            file.setPost(post);
        }
    }
}
