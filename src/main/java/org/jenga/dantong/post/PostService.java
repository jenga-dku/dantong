package org.jenga.dantong.post;

import org.jenga.dantong.post.model.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public int savePost(Post post){
        postRepository.save(post);

        return post.getPostId();
    }

    public int deletePost(int postId){
        Post post = postRepository.findByPostId(postId);
        post.setShown(false);
        postRepository.save(post);

        return postId;
    }

    public List<Post> showPost() {
        List<Post> posts = postRepository.findRemainPost();

        return posts;
    }

    public Post updatePost(int postId, String content) {
        Post post = postRepository.findByPostId(postId);

        post.setContent(content);
        postRepository.save(post);

        return post;
    }
}
