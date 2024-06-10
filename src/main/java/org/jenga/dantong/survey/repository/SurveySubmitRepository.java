package org.jenga.dantong.survey.repository;

import java.util.List;
import java.util.Optional;
import org.jenga.dantong.survey.model.entity.Survey;
import org.jenga.dantong.survey.model.entity.SurveySubmit;
import org.jenga.dantong.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveySubmitRepository extends JpaRepository<SurveySubmit, Long> {

    Optional<SurveySubmit> findSurveySubmitByUserAndSurvey(User user, Survey survey);

    List<SurveySubmit> findSurveySubmitBySurvey(Survey survey);

    Long countBySurvey(Survey survey);
}
