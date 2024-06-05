package org.jenga.dantong.survey.repository;

import org.jenga.dantong.survey.model.entity.SurveyItem;
import org.jenga.dantong.survey.model.entity.SurveyReply;
import org.jenga.dantong.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyReplyRepository extends JpaRepository<SurveyReply, Long> {

    List<SurveyReply> findAllBySurveyItem(SurveyItem surveyItem);

    Optional<SurveyReply> findSurveyReplyByUserAndSurveyItem(User user, SurveyItem surveyItem);

    SurveyReply findBySurveyItemAndUserId(SurveyItem surveyItem, Long userId);

    SurveyReply findBySurveyItemAndReplyId(SurveyItem surveyItem, Long replyId);
}
