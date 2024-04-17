package org.jenga.dantong.post;

import org.jenga.dantong.post.model.entity.Post;
import org.jenga.dantong.post.model.entity.QPost;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> findRemainPost();
}
