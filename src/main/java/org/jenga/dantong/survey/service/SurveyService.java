package org.jenga.dantong.survey.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.survey.model.dto.*;
import org.jenga.dantong.survey.model.entity.Survey;
import org.jenga.dantong.survey.model.entity.SurveyItem;
import org.jenga.dantong.survey.repository.SurveyItemRepository;
import org.jenga.dantong.survey.repository.SurveyReplyRepository;
import org.jenga.dantong.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyItemRepository surveyItemRepository;
    private final SurveyReplyRepository surveyReplyRepository;

    public int create(SurveyCreateRequest surveyCreate) {

        Survey survey = new Survey(
                surveyCreate.getTitle(),
                surveyCreate.getDescription(),
                surveyCreate.getStartTime(),
                surveyCreate.getEndTime()
        );

        surveyRepository.save(survey);

        List<SurveyItemCreateRequest> item = surveyCreate.getSurveyItems();

        item.stream()
                .map(currItem -> SurveyItem.builder()
                        .survey(survey)
                        .title(currItem.getTitle())
                        .tag(currItem.getTag())
                        .description(currItem.getDescription())
                        .build())
                .forEach(surveyItemRepository::save);

        log.info(survey.getTitle());
        log.info(survey.getDescription());
        log.info(String.valueOf(survey.getStartTime()));
        log.info(String.valueOf(survey.getEndTime()));

        return survey.getSurveyId();
    }


    public int updateSurvey(int surveyId, SurveyUpdateRequest surveyUpdate) {

        Survey survey = surveyRepository.findBySurveyId(surveyId);

        survey.setTitle(surveyUpdate.getTitle());
        survey.setDescription(surveyUpdate.getDescription());
        survey.setStartTime(surveyUpdate.getStartTime());
        survey.setEndTime(surveyUpdate.getEndTime());

        List<SurveyItemUpdateRequest> itemUpdate = surveyUpdate.getSurveyItems();

        itemUpdate.stream()
                .filter(currItem -> surveyItemRepository.findBySurveyItemId(currItem.getSurveyItemId()) == null || (surveyId == (surveyItemRepository.findBySurveyItemId(currItem.getSurveyItemId()).getSurvey().getSurveyId())))
                .map(currItem -> {
                    SurveyItem item = surveyItemRepository.findBySurveyItemId(currItem.getSurveyItemId());

                    if (item != null) {
                        item.setTitle(currItem.getTitle());
                        item.setTag(currItem.getTag());
                        item.setDescription(currItem.getDescription());

                        return item;
                    } else {
                        return SurveyItem.builder()
                                .survey(survey)
                                .surveyItemId(currItem.getSurveyItemId())
                                .title(currItem.getTitle())
                                .tag(currItem.getTag())
                                .description(currItem.getDescription())
                                .build();
                    }
                })
                .forEach(surveyItemRepository::save);

        surveyRepository.save(survey);

        return survey.getSurveyId();
    }

    public void deleteSurveyItem(int surveyId, int itemId) {
        SurveyItem item = surveyItemRepository.findBySurvey_SurveyIdAndSurveyItemId(surveyId, itemId);

        item.setShown(false);
        surveyItemRepository.save(item);
    }


    public void deleteSurvey(int surveyId) {
        Survey survey = surveyRepository.findBySurveyId(surveyId);

        survey.setShown(false);
        surveyRepository.save(survey);
    }

    public SurveyResponse viewSurvey(int surveyId) {
        Survey survey = surveyRepository.findBySurveyId(surveyId);
        List<SurveyItem> items = surveyItemRepository.findBySurvey_SurveyIdAndShownTrue(surveyId);

        List<SurveyItemResponse> responseItems = items.stream()
                .map(currItem -> SurveyItemResponse.builder()
                        .surveyItemId(currItem.getSurveyItemId())
                        .title(currItem.getTitle())
                        .tag(currItem.getTag())
                        .description(currItem.getDescription())
                        .build()).toList();

        SurveyResponse response = SurveyResponse.builder()
                .title(survey.getTitle())
                .description(survey.getDescription())
                .startTime(survey.getStartTime())
                .endTime(survey.getEndTime())
                .surveyItems(responseItems)
                .build();

        return response;
    }


    public int reply() {
        return 1234;
    }


    public int updateReply() {
        return 1234;
    }

}
