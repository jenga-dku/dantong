package org.jenga.dantong.survey.service;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jenga.dantong.survey.exception.SurveyNotFoundException;
import org.jenga.dantong.survey.model.dto.request.SurveySubmitCreateRequest;
import org.jenga.dantong.survey.model.entity.Survey;
import org.jenga.dantong.survey.model.entity.SurveyReply;
import org.jenga.dantong.survey.model.entity.SurveySubmit;
import org.jenga.dantong.survey.repository.SurveyRepository;
import org.jenga.dantong.survey.repository.SurveySubmitRepository;
import org.jenga.dantong.user.exception.UserNotFoundException;
import org.jenga.dantong.user.model.entity.User;
import org.jenga.dantong.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurveySubmitService {

    private final SurveyReplyService surveyReplyService;
    private final UserRepository userRepository;
    private final SurveySubmitRepository surveySubmitRepository;
    private final SurveyRepository surveyRepository;

    @Transactional
    public void createSubmit(SurveySubmitCreateRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Survey survey = surveyRepository.findById(request.getSurveyId())
            .orElseThrow(SurveyNotFoundException::new);

        SurveySubmit surveySubmit = new SurveySubmit(user, survey);
        List<SurveyReply> surveyReplies = surveyReplyService.createReply(request.getReplyRequest(),
            surveySubmit, userId);

        surveySubmit.setSurveyReplies(surveyReplies);

        surveySubmitRepository.save(surveySubmit);
    }
}
