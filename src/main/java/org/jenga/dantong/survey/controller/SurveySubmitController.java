package org.jenga.dantong.survey.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.global.auth.jwt.AppAuthentication;
import org.jenga.dantong.global.base.UserAuth;
import org.jenga.dantong.survey.model.dto.request.SurveyReplyUpdateRequest;
import org.jenga.dantong.survey.model.dto.request.SurveySubmitCreateRequest;
import org.jenga.dantong.survey.model.dto.response.SurveyUserReplyResponse;
import org.jenga.dantong.survey.service.SurveyReplyService;
import org.jenga.dantong.survey.service.SurveySubmitService;
import org.jenga.dantong.user.model.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/submit")
@RequiredArgsConstructor
@RestController
public class SurveySubmitController {

    private final SurveyReplyService surveyReplyService;
    private final SurveySubmitService surveySubmitService;

    @GetMapping("/{surveyItemId}")
    public ResponseEntity<List<SurveyUserReplyResponse>> findAllReply(
        @PathVariable("surveyItemId") Long surveyItemId) {
        List<SurveyUserReplyResponse> reply = surveyReplyService.findAllReply(surveyItemId);

        return ResponseEntity.ok(reply);
    }

    @GetMapping("/user/{surveyId}")
    @UserAuth
    public ResponseEntity<List<SurveyUserReplyResponse>> findUserReply(
        @PathVariable("surveyId") Long surveyId, AppAuthentication user) {
        List<SurveyUserReplyResponse> reply = surveyReplyService.findUserReply(surveyId,
            user.getUserId());

        return ResponseEntity.ok(reply);
    }

    @PostMapping()
    @UserAuth
    public void createReply(@RequestBody SurveySubmitCreateRequest request,
        AppAuthentication auth) {
        log.info(auth.getUserId().toString());
        surveySubmitService.createSubmit(request, auth.getUserId());
    }

    @PatchMapping("/{surveyId}")
    public void updateReply(@RequestBody List<SurveyReplyUpdateRequest> survey) {

        surveyReplyService.updateReply(survey);
    }

    @DeleteMapping("/{surveyId}")
    public void deleteUserReply(@PathVariable(name = "surveyId") Long surveyId,
        @AuthenticationPrincipal User user) {

        surveyReplyService.deleteUserReply(surveyId, user.getId());
    }
}
