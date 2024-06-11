package org.jenga.dantong.survey.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.global.util.Util;
import org.jenga.dantong.post.exception.PermissionDeniedException;
import org.jenga.dantong.survey.exception.SurveyItemNotFoundException;
import org.jenga.dantong.survey.exception.SurveyNotFoundException;
import org.jenga.dantong.survey.exception.SurveyReplyNotFoundException;
import org.jenga.dantong.survey.model.dto.request.SurveyReplyCreateRequest;
import org.jenga.dantong.survey.model.dto.request.SurveyReplyUpdateRequest;
import org.jenga.dantong.survey.model.dto.response.AllRepliesResponse;
import org.jenga.dantong.survey.model.dto.response.SurveyItemResponse;
import org.jenga.dantong.survey.model.dto.response.SurveyUserAllReplyResponse;
import org.jenga.dantong.survey.model.dto.response.SurveyUserReplyResponse;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<SurveyUserReplyResponse> findAllReplyBySurveyItem(Long surveyItemId) {
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

    public List<AllRepliesResponse> findAllReplyBySurvey(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(SurveyNotFoundException::new);
        List<SurveyItem> surveyItems = surveyItemRepository.findBySurvey(survey);
        return surveyItems.stream()
                .map(surveyItem -> {
                            List<SurveyUserReplyResponse> replyResponseList = surveyReplyRepository.findAllBySurveyItem(
                                            surveyItem).stream()
                                    .map(reply -> {
                                                SurveyUserReplyResponse replyResponse = SurveyUserReplyResponse.builder()
                                                        .surveyItemId(reply.getSurveyItem().getSurveyItemId())
                                                        .content(reply.getContent())
                                                        .build();
                                                return replyResponse;
                                            }
                                    ).collect(Collectors.toList());
                            SurveyItemResponse surveyItemResponse = new SurveyItemResponse(surveyItem);
                            return AllRepliesResponse.builder()
                                    .surveyItemResponse(surveyItemResponse)
                                    .replies(replyResponseList)
                                    .build();
                        }
                )
                .collect(Collectors.toList());
    }

    @Transactional
    public List<SurveyUserAllReplyResponse> findAllUserReply(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        List<SurveySubmit> submits = surveySubmitRepository.findByUser(user);

        List<SurveyUserAllReplyResponse> response = new ArrayList<>();

        submits.stream()
                .forEach(currSubmit -> {
                    Survey survey = currSubmit.getSurvey();

                    response.add(
                            SurveyUserAllReplyResponse.builder()
                                    .surveyId(survey.getSurveyId())
                                    .title(survey.getTitle())
                                    .startDate(survey.getStartTime())
                                    .endDate(survey.getEndTime())
                                    .status(Util.getProgress(survey))
                                    .build()
                    );

                });


        return response;
    }


    @Transactional
    public List<SurveyUserReplyResponse> findUserReply(Long surveyId, Long userId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(SurveyNotFoundException::new);
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        List<SurveyItem> items = surveyItemRepository.findBySurvey(survey);

        List<SurveyReply> reply = new ArrayList<>();

        items.forEach(currItem -> {
            SurveyReply surveyReply = surveyReplyRepository.findBySurveyItemAndUser(currItem, user)
                    .orElseThrow(SurveyReplyNotFoundException::new);

            reply.add(surveyReply);
        });

        return reply.stream().map(
                currReply -> SurveyUserReplyResponse.builder()
                        .surveyItemId(currReply.getSurveyItem().getSurveyItemId())
                        .content(currReply.getContent()).build()).toList();
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
    public void updateReply(Long surveyId, List<SurveyReplyUpdateRequest> request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        request.forEach(currRequest -> {
            SurveyItem item = surveyItemRepository.findById(currRequest.getSurveyItemId())
                    .orElseThrow(SurveyItemNotFoundException::new);
            SurveyReply surveyReply = surveyReplyRepository.findBySurveyItemAndUser(item, user)
                    .orElseThrow(SurveyReplyNotFoundException::new);

            if (!item.getSurvey().getSurveyId().equals(surveyId)) {
                throw new PermissionDeniedException();
            }

            surveyReply.setContent(currRequest.getContent());
        });
    }

    @Transactional
    public void deleteUserReply(Long surveyId, Long userId) {

        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(SurveyNotFoundException::new);
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        List<SurveyItem> items = surveyItemRepository.findBySurvey(survey);

        List<SurveyReply> reply = items.stream()
                .map(currItem ->
                        surveyReplyRepository.findBySurveyItemAndUser(currItem, user)
                                .orElseThrow(SurveyReplyNotFoundException::new)).toList();

        surveyReplyRepository.deleteAll(reply);
    }
}