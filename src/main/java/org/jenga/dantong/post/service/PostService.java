package org.jenga.dantong.post.service;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.global.s3.model.dto.FileUploadRequest;
import org.jenga.dantong.global.s3.model.dto.RequestFile;
import org.jenga.dantong.global.s3.service.FileUploadService;
import org.jenga.dantong.post.exception.PermissionDeniedException;
import org.jenga.dantong.post.exception.PostNofFoundException;
import org.jenga.dantong.post.model.dto.PostCreateRequest;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;

    @Transactional
    public int savePost(PostCreateRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Post post = request.toEntity(user);
        log.info(request.getTitle());
        if (request.getImageFiles() != null) {
            saveFiles(request.getImageFiles(), post);
            log.info("여기 들어왔나");
        }
        Post savedPost = postRepository.save(post);
        return savedPost.getPostId();
    }

    @Transactional
    public PostResponse findPost(int postId) {
        Post post = postRepository.findByPostId(postId).orElseThrow(PostNofFoundException::new);
        String progress = getProgress(post);

        return new PostResponse(post, progress);
    }

    @Transactional
    public int deletePost(int postId) {
        Post post = postRepository.findByPostId(postId).orElseThrow(PostNofFoundException::new);
        post.setShown(false);
        postRepository.save(post);

        return postId;
    }

    @Transactional
    public Page<PostResponse> showAllPost(Pageable pageable) {
        Page<Post> posts = postRepository.findByShownTrue(pageable);

        Page<PostResponse> postResponses = posts.map(currPost -> {
            String progress = getProgress(currPost);
            return new PostResponse(currPost, progress);
        });

        return postResponses;
    }

    @Transactional
    public Page<PostResponse> showByCategory(Category category, Pageable pageable) {
        Page<Post> posts = postRepository.findByCategoryAndShownTrue(category, pageable);

        Page<PostResponse> postResponses = posts.map(currPost -> {
            String progress = getProgress(currPost);
            return new PostResponse(currPost, progress);
        });

        return postResponses;
    }

    @Transactional
    public int updatePost(PostUpdateRequest request, Long userId) {
        Post post = postRepository.findByPostId(request.getPostId())
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

    private static String getProgress(Post post) {
        String progress;
        if (post.getStartDate().isAfter(LocalDateTime.now())) {
            progress = "진행전";
        } else if (post.getStartDate().isBefore(LocalDateTime.now()) && post.getEndDate()
            .isAfter(LocalDateTime.now())) {
            progress = "진행중";
        } else {
            progress = "종료";
        }
        return progress;
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
    }
}
