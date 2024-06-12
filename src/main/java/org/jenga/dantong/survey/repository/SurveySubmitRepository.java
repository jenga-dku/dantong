package org.jenga.dantong.survey.repository;

import org.jenga.dantong.survey.model.entity.Survey;
import org.jenga.dantong.survey.model.entity.SurveySubmit;
import org.jenga.dantong.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SurveySubmitRepository extends JpaRepository<SurveySubmit, Long> {

    Optional<SurveySubmit> findSurveySubmitByUserAndSurvey(User user, Survey survey);

    List<SurveySubmit> findSurveySubmitBySurvey(Survey survey);

    Long countBySurvey(Survey survey);

    Page<SurveySubmit> findByUser(User user, Pageable pageable);

    List<SurveySubmit> findByUser(User user);
}
