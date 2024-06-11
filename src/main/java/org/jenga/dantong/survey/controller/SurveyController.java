package org.jenga.dantong.survey.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.global.auth.jwt.AppAuthentication;
import org.jenga.dantong.global.base.AdminAuth;
import org.jenga.dantong.global.base.UserAuth;
import org.jenga.dantong.post.model.entity.Category;
import org.jenga.dantong.survey.model.dto.request.SurveyCreateRequest;
import org.jenga.dantong.survey.model.dto.request.SurveyUpdateRequest;
import org.jenga.dantong.survey.model.dto.response.SurveyAdminResponse;
import org.jenga.dantong.survey.model.dto.response.SurveyIdInfoResponse;
import org.jenga.dantong.survey.model.dto.response.SurveyResponse;
import org.jenga.dantong.survey.model.dto.response.TicketResponse;
import org.jenga.dantong.survey.service.SurveyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @UserAuth
    public ResponseEntity<SurveyIdInfoResponse> update(@PathVariable("surveyId") Long id,
        @RequestBody @Validated SurveyUpdateRequest survey,
        AppAuthentication auth) {

        Long surveyId = surveyService.updateSurvey(id, survey, auth.getUserId());

        return ResponseEntity.ok(SurveyIdInfoResponse.builder().surveyId(surveyId).build());
    }

    @DeleteMapping("/{surveyId}")
    @UserAuth
    public void deleteSurvey(@PathVariable("surveyId") Long surveyId, AppAuthentication auth) {

        surveyService.deleteSurvey(surveyId, auth.getUserId());
    }

    @DeleteMapping("/{surveyId}/{surveyItemId}")
    @UserAuth
    public void deleteSurveyItem(@PathVariable("surveyId") Long surveyId,
        @PathVariable("surveyItemId") Long surveyItemId,
        AppAuthentication auth) {

        surveyService.deleteSurveyItem(surveyId, surveyItemId, auth.getUserId());
    }

    @GetMapping("/admin/surveys")
    @AdminAuth
    public ResponseEntity<Page<SurveyAdminResponse>> getSurveyInfos(Pageable pageable) {
        Page<SurveyAdminResponse> surveyInfos = surveyService.getSurveyInfos(pageable);
        return ResponseEntity.ok(surveyInfos);
    }

    @GetMapping("/ticket")
    @UserAuth
    public ResponseEntity<List<TicketResponse>> tickets(Category category, AppAuthentication auth) {
        List<TicketResponse> tickets = surveyService.getTickets(auth.getUserId());
        return ResponseEntity.ok(tickets);
    }
}
