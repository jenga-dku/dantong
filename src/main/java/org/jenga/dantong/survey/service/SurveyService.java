package org.jenga.dantong.survey.service;

import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.survey.model.dto.SurveyItemResponse;
import org.jenga.dantong.survey.model.dto.SurveyItemSaveRequest;
import org.jenga.dantong.survey.model.dto.SurveyResponse;
import org.jenga.dantong.survey.model.dto.SurveySaveRequest;
import org.jenga.dantong.survey.model.entity.Survey;
import org.jenga.dantong.survey.model.entity.SurveyItem;
import org.jenga.dantong.survey.repository.SurveyItemRepository;
import org.jenga.dantong.survey.repository.SurveyReplyRepository;
import org.jenga.dantong.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SurveyService {

    private SurveyRepository surveyRepository;
    private SurveyItemRepository surveyItemRepository;
    private SurveyReplyRepository surveyReplyRepository;

    public SurveyService(SurveyRepository surveyRepository, SurveyItemRepository surveyItemRepository, SurveyReplyRepository surveyReplyRepository) {
        this.surveyRepository = surveyRepository;
        this.surveyItemRepository = surveyItemRepository;
        this.surveyReplyRepository = surveyReplyRepository;
    }

    public int create(SurveySaveRequest surveyCreate) {

        Survey survey = new Survey(
                surveyCreate.getTitle(),
                surveyCreate.getDescription(),
                surveyCreate.getStartTime(),
                surveyCreate.getEndTime()
        );

        surveyRepository.save(survey);

        for (SurveyItemSaveRequest currItem : surveyCreate.getSurveyItems()) {
            SurveyItem item = SurveyItem.builder()
                    .title(currItem.getTitle())
                    .tag(currItem.getTag())
                    .description(currItem.getDescription())
                    .build();

            log.info(item.getTitle());
            log.info(item.getDescription());
            log.info(String.valueOf(item.getTag()));
            log.info(String.valueOf(item.isShown()));


            item.setSurvey(survey);
            surveyItemRepository.save(item);
        }

        log.info(survey.getTitle());
        log.info(survey.getDescription());
        log.info(String.valueOf(survey.getStartTime()));
        log.info(String.valueOf(survey.getEndTime()));

        return survey.getSurveyId();
    }


    public int updateSurvey(int surveyId, SurveySaveRequest surveyUpdate) {

        Survey survey = surveyRepository.findBySurveyId(surveyId);

        survey.setTitle(surveyUpdate.getTitle());
        survey.setDescription(surveyUpdate.getDescription());
        survey.setStartTime(surveyUpdate.getStartTime());
        survey.setEndTime(surveyUpdate.getEndTime());

        List<SurveyItem> items = surveyItemRepository.findBySurvey_SurveyId(surveyId);
        List<SurveyItemSaveRequest> itemUpdate = surveyUpdate.getSurveyItems();

        int index = 0;
        for (SurveyItem currItem : items) {
            currItem.setTitle(itemUpdate.get(index).getTitle());
            currItem.setTag(itemUpdate.get(index).getTag());
            currItem.setDescription(itemUpdate.get(index).getDescription());

            log.info(currItem.getTitle());
            log.info(currItem.getDescription());
            log.info(String.valueOf(currItem.getTag()));

            surveyItemRepository.save(currItem);

            index++;
        }

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
        List<SurveyItemResponse> responseItems = new ArrayList<SurveyItemResponse>();

        for (SurveyItem currItem : items) {
            SurveyItemResponse item = new SurveyItemResponse();
            item.setTag(currItem.getTag());
            item.setTitle(currItem.getTitle());
            item.setDescription(currItem.getDescription());

            responseItems.add(item);
        }

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
