package org.jenga.dantong.survey.repository;

import org.jenga.dantong.survey.model.entity.Survey;
import org.jenga.dantong.survey.model.entity.SurveyItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyItemRepository extends JpaRepository<SurveyItem, Long> {

    List<SurveyItem> findBySurvey(Survey survey);

    Optional<SurveyItem> findBySurvey_SurveyIdAndSurveyItemId(Long surveyId, Long surveyItemId);

    List<SurveyItem> findBySurvey_SurveyIdAndShownTrue(Long surveyId);
}
