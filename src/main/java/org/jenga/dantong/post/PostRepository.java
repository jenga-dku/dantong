package org.jenga.dantong.post;

import org.jenga.dantong.post.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom{

    Post findByPostId(int postId);
}
