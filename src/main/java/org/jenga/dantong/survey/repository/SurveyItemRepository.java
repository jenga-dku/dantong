package org.jenga.dantong.survey.repository;

import org.jenga.dantong.survey.model.entity.Survey;
import org.jenga.dantong.survey.model.entity.SurveyItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyItemRepository extends JpaRepository<SurveyItem, Long> {

    List<SurveyItem> findBySurvey(Survey survey);

    List<SurveyItem> findBySurveyAndShownTrue(Survey survey);
}
