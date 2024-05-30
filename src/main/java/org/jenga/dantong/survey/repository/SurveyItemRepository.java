package org.jenga.dantong.survey.repository;

import org.jenga.dantong.survey.model.entity.SurveyItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyItemRepository extends JpaRepository<SurveyItem, Long> {
    SurveyItem findBySurveyItemId(Long itemId);

    List<SurveyItem> findBySurvey_SurveyId(Long surveyId);

    SurveyItem findBySurvey_SurveyIdAndSurveyItemId(Long surveyId, Long surveyItemId);

    List<SurveyItem> findBySurvey_SurveyIdAndShownTrue(Long surveyId);
}
