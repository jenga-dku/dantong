package org.jenga.dantong.survey.repository;

import org.jenga.dantong.survey.model.entity.SurveyItem;
import org.jenga.dantong.survey.model.entity.SurveyReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyReplyRepository extends JpaRepository<SurveyReply, Long> {

    List<SurveyReply> findBySurveyItem(SurveyItem surveyItem);

    SurveyReply findBySurveyItemAndUserId(SurveyItem surveyItem, Long userId);

    SurveyReply findBySurveyItemAndReplyId(SurveyItem surveyItem, Long replyId);
}
