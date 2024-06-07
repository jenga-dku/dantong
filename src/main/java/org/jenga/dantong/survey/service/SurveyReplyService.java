package org.jenga.dantong.survey.service;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.survey.exception.SurveyItemNotFoundException;
import org.jenga.dantong.survey.exception.SurveyNotFoundException;
import org.jenga.dantong.survey.model.dto.SurveyReplyCreateRequest;
import org.jenga.dantong.survey.model.dto.SurveyReplyUpdateRequest;
import org.jenga.dantong.survey.model.dto.SurveyUserReplyResponse;
import org.jenga.dantong.survey.model.entity.Survey;
import org.jenga.dantong.survey.model.entity.SurveyItem;
import org.jenga.dantong.survey.model.entity.SurveyReply;
import org.jenga.dantong.survey.model.entity.SurveySubmit;
import org.jenga.dantong.survey.repository.SurveyItemRepository;
import org.jenga.dantong.survey.repository.SurveyReplyRepository;
import org.jenga.dantong.survey.repository.SurveyRepository;
import org.jenga.dantong.survey.repository.SurveySubmitRepository;
import org.jenga.dantong.user.exception.UserNotFoundException;
import org.jenga.dantong.user.model.entity.User;
import org.jenga.dantong.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SurveyReplyService {

    private final SurveyReplyRepository surveyReplyRepository;
    private final SurveyItemRepository surveyItemRepository;
    private final SurveyRepository surveyRepository;
    private final SurveySubmitRepository surveySubmitRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<SurveyUserReplyResponse> findAllReply(Long surveyItemId) {
        SurveyItem surveyItem = surveyItemRepository.findById(surveyItemId)
            .orElseThrow(SurveyItemNotFoundException::new);
        List<SurveyReply> replies = surveyReplyRepository.findAllBySurveyItem(surveyItem);

        return replies.stream()
            .map(reply -> SurveyUserReplyResponse.builder()
                .surveyItemId(reply.getSurveyItem().getSurveyItemId())
                .content(reply.getContent())
                .build())
            .toList();
    }

    @Transactional
    public List<SurveyUserReplyResponse> findUserReply(Long surveyId, Long userId) {
        Survey survey = surveyRepository.findById(surveyId)
            .orElseThrow(SurveyNotFoundException::new);
        List<SurveyItem> items = surveyItemRepository.findBySurvey(survey);

        List<SurveyReply> reply = items.stream().map(
            currItem -> surveyReplyRepository.findBySurveyItemAndUserId(
                currItem, userId)).toList();

        List<SurveyUserReplyResponse> responseReplys = reply.stream().map(
            currReply -> SurveyUserReplyResponse.builder()
                .surveyItemId(currReply.getSurveyItem().getSurveyItemId())
                .content(currReply.getContent()).build()).toList();

        return responseReplys;
    }


    @Transactional
    public List<SurveyReply> createReply(List<SurveyReplyCreateRequest> request,
        SurveySubmit submit, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return request.stream().map(currRequest -> {
            SurveyReply reply = new SurveyReply(currRequest.getContent(), submit, user);

            reply.setSurveyItem(surveyItemRepository.findById(currRequest.getSurveyItemId())
                .orElseThrow(SurveyItemNotFoundException::new));
            surveyReplyRepository.save(reply);
            return reply;
        }).toList();
    }


    @Transactional
    public void updateReply(List<SurveyReplyUpdateRequest> request) {

        request.forEach(currRequest -> {

            SurveyItem item = surveyItemRepository.findById(currRequest.getSurveyItemId())
                .orElseThrow(SurveyItemNotFoundException::new);

            SurveyReply reply = surveyReplyRepository.findBySurveyItemAndReplyId(
                item, currRequest.getReplyId()
            );

            reply.setContent(currRequest.getContent());
        });
    }

    @Transactional
    public void deleteUserReply(Long surveyId, Long userId) {

        Survey survey = surveyRepository.findById(surveyId)
            .orElseThrow(SurveyNotFoundException::new);

        List<SurveyItem> items = surveyItemRepository.findBySurvey(survey);

        List<SurveyReply> reply = items.stream()
            .map(currItem ->
                surveyReplyRepository.findBySurveyItemAndUserId(currItem, userId)).toList();

        reply.forEach(SurveyReply::deleteReply);
    }
}