package org.jenga.dantong.global.util;

import java.time.LocalDateTime;
import org.jenga.dantong.post.model.entity.Post;

public class Util {

    public static String getProgress(Post post) {
        String progress;
        if (post.getStartDate().isAfter(LocalDateTime.now())) {
            progress = "진행전";
        } else if (post.getStartDate().isBefore(LocalDateTime.now()) && post.getEndDate()
            .isAfter(LocalDateTime.now())) {
            progress = "진행중";
        } else {
            progress = "종료";
        }
        return progress;
    }
}
