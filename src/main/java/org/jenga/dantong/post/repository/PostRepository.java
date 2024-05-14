package org.jenga.dantong.post.repository;

import java.util.List;
import java.util.Optional;
import org.jenga.dantong.post.model.entity.Category;
import org.jenga.dantong.post.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByPostId(int postId);

    List<Post> findByShownTrue();

    List<Post> findByCategoryAndShownTrue(Category category);
}
