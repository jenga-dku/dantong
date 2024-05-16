package org.jenga.dantong.post.service;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.global.auth.jwt.AppAuthentication;
import org.jenga.dantong.post.exception.PermissionDeniedException;
import org.jenga.dantong.post.exception.PostNofFoundException;
import org.jenga.dantong.post.model.dto.PostCreateRequest;
import org.jenga.dantong.post.model.dto.PostResponse;
import org.jenga.dantong.post.model.dto.PostUpdateRequest;
import org.jenga.dantong.post.model.entity.Category;
import org.jenga.dantong.post.model.entity.Post;
import org.jenga.dantong.post.repository.PostRepository;
import org.jenga.dantong.user.exception.UserNotFoundException;
import org.jenga.dantong.user.model.entity.User;
import org.jenga.dantong.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public int savePost(PostCreateRequest postCreateRequest, AppAuthentication auth) {
        User user = userRepository.findById(auth.getUserId()).orElseThrow(
            UserNotFoundException::new);
        Post post = Post.builder()
            .user(user)
            .title(postCreateRequest.getTitle())
            .description(postCreateRequest.getDescription())
            .content(postCreateRequest.getContent())
            .category(postCreateRequest.getCategory())
            .startDate(postCreateRequest.getStart_time())
            .endDate(postCreateRequest.getEnd_time())
            .shown(postCreateRequest.isShown())
            .build();

        postRepository.save(post);

        return post.getPostId();
    }

    @Transactional
    public PostResponse findPost(int postId) {
        Post post = postRepository.findByPostId(postId).orElseThrow(PostNofFoundException::new);

        log.info(String.valueOf(post.getPostId()));

        String progress;

        if (post.getStartDate().isAfter(LocalDateTime.now())) {
            progress = "진행전";
        } else if (post.getStartDate().isBefore(LocalDateTime.now()) && post.getEndDate()
            .isAfter(LocalDateTime.now())) {
            progress = "진행중";
        } else {
            progress = "종료";
        }

        PostResponse response = new PostResponse(post, progress);

        return response;
    }

    @Transactional
    public int deletePost(int postId) {
        Post post = postRepository.findByPostId(postId).orElseThrow(PostNofFoundException::new);
        post.setShown(false);
        postRepository.save(post);

        return postId;
    }

    @Transactional
    public List<PostResponse> showAllPost() {
        List<Post> posts = postRepository.findByShownTrue();

        List<PostResponse> postResponses = posts.stream()
            .map(currPost -> {
                String progress;

                if (currPost.getStartDate().isAfter(LocalDateTime.now())) {
                    progress = "진행전";
                } else if (currPost.getStartDate().isBefore(LocalDateTime.now())
                    && currPost.getEndDate().isAfter(LocalDateTime.now())) {
                    progress = "진행중";
                } else {
                    progress = "종료";
                }

                return new PostResponse(currPost, progress);
            }).toList();

        return postResponses;
    }

    @Transactional
    public List<PostResponse> showByCategory(Category category) {
        List<Post> posts = postRepository.findByCategoryAndShownTrue(category);

        List<PostResponse> postResponses = posts.stream()
            .map(currPost -> {
                String progress;

                if (currPost.getStartDate().isAfter(LocalDateTime.now())) {
                    progress = "진행전";
                } else if (currPost.getStartDate().isBefore(LocalDateTime.now())
                    && currPost.getEndDate().isAfter(LocalDateTime.now())) {
                    progress = "진행중";
                } else {
                    progress = "종료";
                }

                return new PostResponse(currPost, progress);
            }).toList();

        return postResponses;
    }

    @Transactional
    public int updatePost(PostUpdateRequest request, AppAuthentication auth) {
        Post post = postRepository.findByPostId(request.getPostId())
            .orElseThrow(PostNofFoundException::new);
        if (!auth.getUserId().equals(post.getUser().getId())) {
            throw new PermissionDeniedException();
        }
        post.setContent(request.getContent());
        post.setTitle(request.getTitle());
        post.setDescription(request.getDescription());
        post.setCategory(request.getCategory());
        post.setStartDate(request.getStart_time());
        post.setEndDate(request.getEnd_time());

        log.info(post.getTitle());

        return post.getPostId();
    }
}
