package org.jenga.dantong.survey.repository;

import org.jenga.dantong.survey.model.entity.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    Page<Survey> findAll(Pageable pageable);
}
