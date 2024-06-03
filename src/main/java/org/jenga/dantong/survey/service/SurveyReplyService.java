package org.jenga.dantong.survey.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.survey.model.dto.SurveyReplyCreateRequest;
import org.jenga.dantong.survey.model.dto.SurveyReplyUpdateRequest;
import org.jenga.dantong.survey.model.dto.SurveyUserReplyResponse;
import org.jenga.dantong.survey.model.entity.SurveyItem;
import org.jenga.dantong.survey.model.entity.SurveyReply;
import org.jenga.dantong.survey.repository.SurveyItemRepository;
import org.jenga.dantong.survey.repository.SurveyReplyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SurveyReplyService {

    private final SurveyReplyRepository surveyReplyRepository;
    private final SurveyItemRepository surveyItemRepository;

    @Transactional
    public List<List<SurveyUserReplyResponse>> findAllReply(Long surveyId) {
        List<SurveyItem> items = surveyItemRepository.findBySurvey_SurveyId(surveyId);

        List<List<SurveyReply>> replys = items.stream()
                .map(currItem ->
                        surveyReplyRepository.findBySurveyItem_SurveyItemId(currItem.getSurveyItemId())).toList();

        List<List<SurveyUserReplyResponse>> response = new ArrayList<>();

        for (List<SurveyReply> reply : replys) {
            response.add(
                    reply.stream()
                            .map(currReply -> SurveyUserReplyResponse.builder()
                                    .surveyItemId(currReply.getSurveyItem().getSurveyItemId())
                                    .content(currReply.getContent())
                                    .build()).toList()
            );

        }
        return response;
    }

    @Transactional
    public List<SurveyUserReplyResponse> findUserReply(Long surveyId, Long userId) {
        List<SurveyItem> items = surveyItemRepository.findBySurvey_SurveyId(surveyId);

        List<SurveyReply> reply = items.stream()
                .map(currItem ->
                        surveyReplyRepository.findBySurveyItem_SurveyItemIdAndUserId(currItem.getSurveyItemId(), userId)).toList();

        List<SurveyUserReplyResponse> responseReplys = reply.stream()
                .map(currReply -> SurveyUserReplyResponse.builder()
                        .surveyItemId(currReply.getSurveyItem().getSurveyItemId())
                        .content(currReply.getContent())
                        .build()).toList();

        return responseReplys;
    }


    @Transactional
    public void createReply(List<SurveyReplyCreateRequest> request) {

        request.forEach(currRequest -> {
            SurveyReply reply = new SurveyReply(
                    currRequest.getContent(),
                    currRequest.getUserId());

            reply.setSurveyItem(surveyItemRepository.findBySurveyItemId(currRequest.getSurveyItemId()));
            surveyReplyRepository.save(reply);
        });
    }

    @Transactional
    public void updateReply(List<SurveyReplyUpdateRequest> request) {

        request.forEach(currRequest -> {
            SurveyReply reply = surveyReplyRepository.findBySurveyItem_SurveyItemIdAndReplyId(
                    currRequest.getSurveyItemId(), currRequest.getReplyId()
            );

            reply.setContent(currRequest.getContent());
        });
    }

    @Transactional
    public void deleteUserReply(Long surveyId, Long userId) {

        List<SurveyItem> items = surveyItemRepository.findBySurvey_SurveyId(surveyId);

        List<SurveyReply> reply = items.stream()
                .map(currItem ->
                        surveyReplyRepository.findBySurveyItem_SurveyItemIdAndUserId(currItem.getSurveyItemId(), userId)).toList();

        reply.forEach(SurveyReply::deleteReply);
    }
}
