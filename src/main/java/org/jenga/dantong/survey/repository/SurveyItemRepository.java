package org.jenga.dantong.survey.repository;

import java.util.List;
import java.util.Optional;
import org.jenga.dantong.survey.model.entity.SurveyItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyItemRepository extends JpaRepository<SurveyItem, Long> {

    SurveyItem findBySurveyItemId(Long itemId);

    List<SurveyItem> findBySurvey_SurveyId(Long surveyId);

    Optional<SurveyItem> findBySurvey_SurveyIdAndSurveyItemId(Long surveyId, Long surveyItemId);

    List<SurveyItem> findBySurvey_SurveyIdAndShownTrue(Long surveyId);
}
