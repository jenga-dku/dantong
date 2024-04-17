package org.jenga.dantong.survey.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.sql.Date;
import java.time.LocalDateTime;
import org.jenga.dantong.global.entity.BaseEntity;

@Entity
public class Survey extends BaseEntity {

    @Id
    @GeneratedValue
    private int surveyId;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
