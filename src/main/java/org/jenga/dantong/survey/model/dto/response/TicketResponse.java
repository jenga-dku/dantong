package org.jenga.dantong.survey.model.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jenga.dantong.post.model.dto.response.PostFileResponse;

@Getter
@Setter
@Builder
public class TicketResponse {

    private Long surveyId;
    private Long postId;
    private String title;
    private LocalDateTime startTime;
    private String description;
    private List<PostFileResponse> postFileResponse;
    private String status;

}
