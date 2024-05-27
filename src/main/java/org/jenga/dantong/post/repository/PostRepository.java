package org.jenga.dantong.post.repository;

import org.jenga.dantong.post.model.entity.Category;
import org.jenga.dantong.post.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Page<Post> findByShownTrue(Pageable pageable);

    Page<Post> findByCategory(Category category, Pageable pageable);
}
