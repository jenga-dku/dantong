package org.jenga.dantong.survey.repository;

import org.jenga.dantong.survey.model.entity.SurveyReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyReplyRepository extends JpaRepository<SurveyReply, Long> {
    SurveyReply findByReplyId(int replyId);

    SurveyReply findBySurveyItem_SurveyItemId(int surveyItemId);

    SurveyReply findBySurveyItem_SurveyItemIdAndReplyId(int surveyItemId, int replyId);
}
