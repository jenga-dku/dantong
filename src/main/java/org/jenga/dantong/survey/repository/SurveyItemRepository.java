package org.jenga.dantong.survey.repository;

import org.jenga.dantong.survey.model.dto.SurveyItemResponse;
import org.jenga.dantong.survey.model.entity.SurveyItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SurveyItemRepository extends JpaRepository<SurveyItem, Long> {
    SurveyItem findBySurveyItemId(int itemId);
    List<SurveyItem> findBySurvey_SurveyId(int surveyId);
    SurveyItem findBySurvey_SurveyIdAndSurveyItemId(int surveyId, int surveyItemId);
    List<SurveyItem> findBySurvey_SurveyIdAndShownTrue(int surveyId);
}
