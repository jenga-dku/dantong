package org.jenga.dantong.survey.repository;

import org.jenga.dantong.survey.model.entity.SurveyReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyReplyRepository extends JpaRepository<SurveyReply, Long> {
    SurveyReply findByReplyId(Long replyId);

    SurveyReply findBySurveyItem_SurveyItemId(Long surveyItemId);

    SurveyReply findBySurveyItem_SurveyItemIdAndUserId(Long surveyItemId, Long userId);

    SurveyReply findBySurveyItem_SurveyItemIdAndReplyId(Long surveyItemId, Long replyId);
}
