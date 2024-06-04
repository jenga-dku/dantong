package org.jenga.dantong.survey.repository;

import java.util.List;
import java.util.Optional;
import org.jenga.dantong.survey.model.entity.SurveyItem;
import org.jenga.dantong.survey.model.entity.SurveyReply;
import org.jenga.dantong.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyReplyRepository extends JpaRepository<SurveyReply, Long> {

    List<SurveyReply> findAllBySurveyItem(SurveyItem surveyItem);

    Optional<SurveyReply> findSurveyReplyByUserAndSurveyItem(User user, SurveyItem surveyItem);

    SurveyReply findBySurveyItem_SurveyItemIdAndUserId(Long surveyItemId, Long userId);

    SurveyReply findBySurveyItem_SurveyItemIdAndReplyId(Long surveyItemId, Long replyId);
}
