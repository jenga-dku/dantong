package org.jenga.dantong.survey.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.jenga.dantong.global.auth.jwt.exception.NotGrantedException;
import org.jenga.dantong.survey.exception.AlreadyHasSubmitException;
import org.jenga.dantong.survey.exception.SurveyNotFoundException;
import org.jenga.dantong.survey.exception.SurveySubmitNotFoundException;
import org.jenga.dantong.survey.model.dto.request.SurveySubmitCreateRequest;
import org.jenga.dantong.survey.model.dto.response.SurveyItemResponse;
import org.jenga.dantong.survey.model.dto.response.SurveyReplyResponse;
import org.jenga.dantong.survey.model.dto.response.SurveySubmitResponse;
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
        surveySubmitRepository.findSurveySubmitByUserAndSurvey(user, survey)
            .ifPresent(surveySubmit -> {
                throw new AlreadyHasSubmitException();
            });
        SurveySubmit surveySubmit = new SurveySubmit(user, survey);
        List<SurveyReply> surveyReplies = surveyReplyService.createReply(request.getReplyRequest(),
            surveySubmit, userId);

        surveySubmit.setSurveyReplies(surveyReplies);

        surveySubmitRepository.save(surveySubmit);
    }

    @Transactional
    public void deleteSubmit(Long submitId, Long userId) {
        SurveySubmit surveySubmit = surveySubmitRepository.findById(submitId)
            .orElseThrow(SurveySubmitNotFoundException::new);
        if (surveySubmit.getUser().getId().equals(userId)) {
            surveySubmitRepository.delete(surveySubmit);
        } else {
            throw new NotGrantedException();
        }
    }

    @Transactional
    public SurveySubmitResponse getSubmit(Long submitId, Long userId) {
        SurveySubmit submit = surveySubmitRepository.findById(submitId)
            .orElseThrow(SurveySubmitNotFoundException::new);
        List<SurveyReplyResponse> surveyReplyResponses = submit.getSurveyReplies().stream()
            .map(surveyReply -> {
                SurveyItemResponse surveyItemResponse = new SurveyItemResponse(
                    surveyReply.getSurveyItem());
                return new SurveyReplyResponse(surveyReply.getReplyId(), surveyItemResponse,
                    surveyReply.getContent());
            }).collect(Collectors.toList());
        if (submit.getUser().getId().equals(userId)) {
            return new SurveySubmitResponse(submit.getSurvey().getSurveyId(), surveyReplyResponses);
        } else {
            throw new NotGrantedException();
        }
    }
}
