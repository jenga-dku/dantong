package org.jenga.dantong.post;

import org.jenga.dantong.post.model.entity.Post;
import org.jenga.dantong.post.model.entity.PostResponseDto;
import org.jenga.dantong.post.model.entity.PostSaveDto;
import org.jenga.dantong.post.model.entity.PostUpdateDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public int savePost(PostSaveDto postSaveDto){
        Post post = Post.builder()
                .userId(postSaveDto.getUserId())
                .title(postSaveDto.getTitle())
                .description(postSaveDto.getDescription())
                .content(postSaveDto.getContent())
                .date(postSaveDto.getDate())
                .shown(postSaveDto.isShown())
                .build();

        System.out.println("**** Post Info ****");
        System.out.println("Title: " + postSaveDto.getTitle());
        System.out.println("Description: " + postSaveDto.getDescription());
        System.out.println("Content: " +postSaveDto.getContent());
        System.out.println("*******************");

        postRepository.save(post);

        return post.getPostId();
    }

    public PostResponseDto findPost(int postId){
        Post post = postRepository.findByPostId(postId);

        return PostResponseDto.builder()
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

    public List<PostResponseDto> showAllPost() {
        List<Post> posts = postRepository.findByShownTrue();
        List<PostResponseDto> postResponseDtos = new ArrayList<>();

        for (Post currPost : posts) {
            postResponseDtos.add(
                    PostResponseDto.builder()
                            .userId(currPost.getUserId())
                            .title(currPost.getTitle())
                            .content(currPost.getContent())
                            .description(currPost.getDescription())
                            .date(currPost.getDate())
                            .build()
            );
        }

        return postResponseDtos;
    }

    public int updatePost(PostUpdateDto updatedPost) {
        Post post = postRepository.findByPostId(updatedPost.getPostId());

        post.setContent(updatedPost.getContent());
        post.setTitle(updatedPost.getTitle());
        post.setDescription(updatedPost.getDescription());
        post.setDate(updatedPost.getUpdateDate());

        System.out.println("**** Post Info ****");
        System.out.println("Title: " + post.getTitle());
        System.out.println("Description: " + post.getDescription());
        System.out.println("Content: " + post.getContent());
        System.out.println("*******************");

        postRepository.save(post);

        return post.getPostId();
    }
}
