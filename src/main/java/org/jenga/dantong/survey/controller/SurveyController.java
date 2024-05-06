package org.jenga.dantong.survey.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.survey.model.dto.SurveyIdInfoRequest;
import org.jenga.dantong.survey.model.dto.SurveyItemIdInfoRequest;
import org.jenga.dantong.survey.model.dto.SurveyResponse;
import org.jenga.dantong.survey.model.dto.SurveySaveRequest;
import org.jenga.dantong.survey.service.SurveyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/survey")
@RestController
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping("/create")
    public String create(@RequestBody SurveySaveRequest survey) throws Exception {

        surveyService.create(survey);

        return "Create survey succeed!";
    }

    @PostMapping("/{surveyId}/update")
    public String update(@ModelAttribute SurveyIdInfoRequest surveyInfo, @RequestBody SurveySaveRequest survey) throws Exception {

        surveyService.updateSurvey(surveyInfo.getSurveyId(), survey);

        return "Update survey succeed!";
    }

    @GetMapping("/{surveyId}/delete")
    public String deleteSurvey(@ModelAttribute SurveyIdInfoRequest surveyInfo) {
        surveyService.deleteSurvey(surveyInfo.getSurveyId());

        return "delete survey succeed!";
    }

    @GetMapping("/{surveyId}/{surveyItemId}/delete")
    public String deleteSurveyItem(@ModelAttribute SurveyIdInfoRequest surveyInfo, @ModelAttribute SurveyItemIdInfoRequest surveyItemInfo) {
        surveyService.deleteSurveyItem(surveyInfo.getSurveyId(), surveyItemInfo.getSurveyItemId());

        return "delete survey succeed!";
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity<SurveyResponse> viewSurvey(@ModelAttribute SurveyIdInfoRequest surveyInfo) {
        SurveyResponse response = surveyService.viewSurvey(surveyInfo.getSurveyId());

        return ResponseEntity.ok(response);
    }
}
