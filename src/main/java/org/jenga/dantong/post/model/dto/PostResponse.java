package org.jenga.dantong.post.model.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.jenga.dantong.post.model.entity.Category;
import org.jenga.dantong.post.model.entity.Post;

@Getter
@Setter
public class PostResponse {

    private int postId;
    private String title;
    private String description;
    private String content;
    private LocalDateTime date;
    private String status;
    private Category category;


    public PostResponse(Post post, String status) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.content = post.getContent();
        this.date = post.getDate();
        this.status = status;
        this.category = post.getCategory();
    }
}
