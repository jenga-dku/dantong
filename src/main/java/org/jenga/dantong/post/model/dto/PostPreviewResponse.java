package org.jenga.dantong.post.model.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.jenga.dantong.post.model.entity.Category;
import org.jenga.dantong.post.model.entity.Post;
import org.jenga.dantong.user.model.dto.UserResponse;

@Getter
@Setter
public class PostPreviewResponse {

    private Long postId;
    private String title;
    private String description;
    private String status;
    private Category category;
    private List<PostFileResponse> postFileResponse;

    private UserResponse userResponse;

    public PostPreviewResponse(Post post, String status, List<PostFileResponse> postFileResponse,
        UserResponse userResponse) {
        this.userResponse = userResponse;
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.status = status;
        this.category = post.getCategory();
        this.postFileResponse = postFileResponse;
    }
}
