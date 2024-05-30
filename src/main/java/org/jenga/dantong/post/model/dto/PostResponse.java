package org.jenga.dantong.post.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.jenga.dantong.post.model.entity.Category;
import org.jenga.dantong.post.model.entity.Post;

import java.util.List;

@Getter
@Setter
public class PostResponse {

    private Long postId;
    private String title;
    private String description;
    private String content;
    private String status;
    private Category category;
    private List<PostFileResponse> postFileResponse;


    public PostResponse(Post post, String status, List<PostFileResponse> postFileResponse) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.content = post.getContent();
        this.status = status;
        this.category = post.getCategory();
        this.postFileResponse = postFileResponse;
    }
}
