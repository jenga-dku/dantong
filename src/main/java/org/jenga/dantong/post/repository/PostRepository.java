package org.jenga.dantong.post.repository;

import org.jenga.dantong.post.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>{

    Post findByPostId(int postId);
    List<Post> findByShownTrue();
}
