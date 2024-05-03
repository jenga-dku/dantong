package org.jenga.dantong.post.model.service;

import org.jenga.dantong.post.model.entity.Post;
import org.jenga.dantong.post.model.dto.PostResponse;
import org.jenga.dantong.post.model.dto.PostSaveRequest;
import org.jenga.dantong.post.model.dto.PostUpdateRequest;
import org.jenga.dantong.post.model.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public int savePost(PostSaveRequest postSaveRequest){
        Post post = Post.builder()
                .userId(postSaveRequest.getUserId())
                .title(postSaveRequest.getTitle())
                .description(postSaveRequest.getDescription())
                .content(postSaveRequest.getContent())
                .date(postSaveRequest.getDate())
                .shown(postSaveRequest.isShown())
                .build();

        postRepository.save(post);

        return post.getPostId();
    }

    public PostResponse findPost(int postId){
        Post post = postRepository.findByPostId(postId);

        return PostResponse.builder()
                .userId(post.getUserId())
                .title(post.getTitle())
                .description(post.getDescription())
                .content(post.getContent())
                .date(post.getDate())
                .build();
    }

    public int deletePost(int postId){
        Post post = postRepository.findByPostId(postId);
        post.setShown(false);
        postRepository.save(post);

        return postId;
    }

    public List<PostResponse> showAllPost() {
        List<Post> posts = postRepository.findByShownTrue();
        List<PostResponse> postResponses = new ArrayList<>();

        for (Post currPost : posts) {
            postResponses.add(
                    PostResponse.builder()
                            .userId(currPost.getUserId())
                            .title(currPost.getTitle())
                            .content(currPost.getContent())
                            .description(currPost.getDescription())
                            .date(currPost.getDate())
                            .build()
            );
        }

        return postResponses;
    }

    public int updatePost(int postId, PostUpdateRequest updatedPost) {
        Post post = postRepository.findByPostId(postId);

        post.setContent(updatedPost.getContent());
        post.setTitle(updatedPost.getTitle());
        post.setDescription(updatedPost.getDescription());
        post.setDate(updatedPost.getUpdateDate());

        postRepository.save(post);

        return post.getPostId();
    }
}
