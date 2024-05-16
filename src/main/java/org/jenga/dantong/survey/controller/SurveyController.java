package org.jenga.dantong.survey.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.survey.model.dto.*;
import org.jenga.dantong.survey.service.SurveyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/survey")
@RequiredArgsConstructor
@RestController
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping()
    public ResponseEntity<SurveyResponse> findSurvey(@ModelAttribute SurveyIdInfoRequest surveyInfo) {
        SurveyResponse response = surveyService.findSurvey(surveyInfo.getSurveyId());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public void create(@RequestBody SurveyCreateRequest survey) throws Exception {

        surveyService.create(survey);
    }

    @PatchMapping("/edit")
    public void update(@RequestBody SurveyUpdateRequest survey) throws Exception {

        surveyService.updateSurvey(survey);
    }

    @DeleteMapping("/deleteSurvey")
    public void deleteSurvey(@ModelAttribute SurveyIdInfoRequest surveyInfo) {

        surveyService.deleteSurvey(surveyInfo.getSurveyId());
    }

    @DeleteMapping("/deleteItem")
    public void deleteSurveyItem(@ModelAttribute SurveyIdInfoRequest surveyInfo, @ModelAttribute SurveyItemIdInfoRequest surveyItemInfo) {

        surveyService.deleteSurveyItem(surveyInfo.getSurveyId(), surveyItemInfo.getSurveyItemId());
    }
}
