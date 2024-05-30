package org.jenga.dantong.survey.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.survey.model.dto.SurveyCreateRequest;
import org.jenga.dantong.survey.model.dto.SurveyIdInfoResponse;
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
    public ResponseEntity<SurveyResponse> findSurvey(@PathVariable("surveyId") Long surveyId) {
        SurveyResponse response = surveyService.findSurvey(surveyId);

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<SurveyIdInfoResponse> create(@RequestBody SurveyCreateRequest survey) throws Exception {

        Long surveyId = surveyService.create(survey);

        return ResponseEntity.ok(SurveyIdInfoResponse.builder().surveyId(surveyId).build());
    }

    @PatchMapping("/{surveyId}")
    public ResponseEntity<SurveyIdInfoResponse> update(@PathVariable("surveyId") Long id, @RequestBody SurveyUpdateRequest survey) throws Exception {

        Long surveyId = surveyService.updateSurvey(id, survey);

        return ResponseEntity.ok(SurveyIdInfoResponse.builder().surveyId(surveyId).build());
    }

    @DeleteMapping("/{surveyId}")
    public void deleteSurvey(@PathVariable("surveyId") Long surveyId) {

        surveyService.deleteSurvey(surveyId);
    }

    @DeleteMapping("/{surveyId}/{surveyItemId}")
    public void deleteSurveyItem(@PathVariable("surveyId") Long surveyId, @PathVariable("surveyItemId") Long surveyItemId) {

        surveyService.deleteSurveyItem(surveyId, surveyItemId);
    }
}
