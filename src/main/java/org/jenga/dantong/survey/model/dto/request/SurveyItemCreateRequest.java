package org.jenga.dantong.survey.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.survey.exception.ItemOptionIsWrongException;
import org.jenga.dantong.survey.model.entity.Tag;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class SurveyItemCreateRequest {

    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String title;

    @NotNull(message = "질문 유형은 필수 입력값입니다.")
    private Tag tag;

    private List<String> options;
    private String description;

    public void tagCheck() {
        if (this.tag.equals(Tag.MULTIPLE)) {
            if (this.options == null || this.options.isEmpty()) {
                throw new ItemOptionIsWrongException();
            } else {
                this.options.forEach(currOption -> {
                    if (currOption.isEmpty() || currOption.isBlank()) {
                        throw new ItemOptionIsWrongException();
                    }
                });
            }
        }
    }
}