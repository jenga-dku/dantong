package org.jenga.dantong.survey.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.global.auth.jwt.AppAuthentication;
import org.jenga.dantong.global.base.UserAuth;
import org.jenga.dantong.survey.model.dto.SurveyCreateRequest;
import org.jenga.dantong.survey.model.dto.SurveyIdInfoResponse;
import org.jenga.dantong.survey.model.dto.SurveyResponse;
import org.jenga.dantong.survey.model.dto.SurveyUpdateRequest;
import org.jenga.dantong.survey.service.SurveyService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @UserAuth
    public ResponseEntity<SurveyIdInfoResponse> create(
        @RequestBody @Validated SurveyCreateRequest survey, AppAuthentication auth) {

        Long surveyId = surveyService.create(survey, auth.getUserId());

        return ResponseEntity.ok(SurveyIdInfoResponse.builder().surveyId(surveyId).build());
    }

    @PatchMapping("/{surveyId}")
    public ResponseEntity<SurveyIdInfoResponse> update(@PathVariable("surveyId") Long id,
        @RequestBody SurveyUpdateRequest survey) throws Exception {

        Long surveyId = surveyService.updateSurvey(id, survey);

        return ResponseEntity.ok(SurveyIdInfoResponse.builder().surveyId(surveyId).build());
    }

    @DeleteMapping("/{surveyId}")
    public void deleteSurvey(@PathVariable("surveyId") Long surveyId) {

        surveyService.deleteSurvey(surveyId);
    }

    @DeleteMapping("/{surveyId}/{surveyItemId}")
    public void deleteSurveyItem(@PathVariable("surveyId") Long surveyId,
        @PathVariable("surveyItemId") Long surveyItemId) {

        surveyService.deleteSurveyItem(surveyId, surveyItemId);
    }
}
