package org.jenga.dantong.survey.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.survey.model.dto.SurveyCreateRequest;
import org.jenga.dantong.survey.model.dto.SurveyResponse;
import org.jenga.dantong.survey.model.dto.SurveyUpdateRequest;
import org.jenga.dantong.survey.service.SurveyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/survey")
@RequiredArgsConstructor
@RestController
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping("/{surveyId}")
    public ResponseEntity<SurveyResponse> findSurvey(@PathVariable("surveyId") int surveyId) {
        SurveyResponse response = surveyService.findSurvey(surveyId);

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

    @DeleteMapping("/deleteSurvey/{surveyId}")
    public void deleteSurvey(@PathVariable("surveyId") int surveyId) {

        surveyService.deleteSurvey(surveyId);
    }

    @DeleteMapping("/deleteItem/{surveyId}/{surveyItemId}")
    public void deleteSurveyItem(@PathVariable("surveyId") int surveyId, @PathVariable("surveyItemId") int surveyItemId) {

        surveyService.deleteSurveyItem(surveyId, surveyItemId);
    }
}
