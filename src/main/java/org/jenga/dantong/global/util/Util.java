package org.jenga.dantong.global.util;

import java.time.LocalDateTime;
import org.jenga.dantong.post.model.entity.Post;
import org.jenga.dantong.survey.model.entity.Survey;

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

    public static String getProgress(Survey survey) {
        String progress;
        if (survey.getStartTime().isAfter(LocalDateTime.now())) {
            progress = "진행전";
        } else if (survey.getStartTime().isBefore(LocalDateTime.now()) && survey.getEndTime()
            .isAfter(LocalDateTime.now())) {
            progress = "진행중";
        } else {
            progress = "종료";
        }
        return progress;
    }
}
