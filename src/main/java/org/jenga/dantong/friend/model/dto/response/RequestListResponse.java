package org.jenga.dantong.friend.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestListResponse {
    String studentId;
    String name;
    Long friendshipId;
}
