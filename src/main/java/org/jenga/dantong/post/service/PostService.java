package org.jenga.dantong.post.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.post.model.dto.PostCreateRequest;
import org.jenga.dantong.post.model.dto.PostResponse;
import org.jenga.dantong.post.model.dto.PostUpdateRequest;
import org.jenga.dantong.post.model.entity.Category;
import org.jenga.dantong.post.model.entity.Post;
import org.jenga.dantong.post.repository.PostRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public int savePost(PostCreateRequest postSaveRequest) {
        Post post = Post.builder()
            .userId(postSaveRequest.getUserId())
            .title(postSaveRequest.getTitle())
            .description(postSaveRequest.getDescription())
            .content(postSaveRequest.getContent())
            .date(postSaveRequest.getDate())
            .category(postSaveRequest.getCategory())
            .startDate(postSaveRequest.getStart_time())
            .endDate(postSaveRequest.getEnd_time())
            .shown(postSaveRequest.isShown())
            .build();

        postRepository.save(post);

        return post.getPostId();
    }

    public PostResponse findPost(int postId) {
        Post post = postRepository.findByPostId(postId);

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

    public int deletePost(int postId) {
        Post post = postRepository.findByPostId(postId);
        post.setShown(false);
        postRepository.save(post);

        return postId;
    }

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

    public int updatePost(int postId, PostUpdateRequest updatedPost) {
        Post post = postRepository.findByPostId(postId);

        post.setContent(updatedPost.getContent());
        post.setTitle(updatedPost.getTitle());
        post.setDescription(updatedPost.getDescription());
        post.setDate(updatedPost.getUpdateDate());
        post.setCategory(updatedPost.getCategory());
        post.setStartDate(updatedPost.getStart_time());
        post.setEndDate(updatedPost.getEnd_time());

        postRepository.save(post);

        return post.getPostId();
    }
}
