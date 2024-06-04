package org.jenga.dantong.survey.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.global.auth.jwt.AppAuthentication;
import org.jenga.dantong.global.base.UserAuth;
import org.jenga.dantong.survey.model.dto.SurveyReplyCreateRequest;
import org.jenga.dantong.survey.model.dto.SurveyReplyUpdateRequest;
import org.jenga.dantong.survey.model.dto.SurveyUserReplyResponse;
import org.jenga.dantong.survey.service.SurveyReplyService;
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
@RequestMapping("/reply")
@RequiredArgsConstructor
@RestController
public class SurveyReplyController {

    private final SurveyReplyService surveyReplyService;

    @GetMapping("/{surveyItemId}")
    public ResponseEntity<List<SurveyUserReplyResponse>> findAllReply(
        @PathVariable("surveyItemId") Long surveyItemId) {
        List<SurveyUserReplyResponse> reply = surveyReplyService.findAllReply(
            surveyItemId);

        return ResponseEntity.ok(reply);
    }

    @GetMapping("/user/{surveyId}")
    public ResponseEntity<List<SurveyUserReplyResponse>> findUserReply(
        @PathVariable("surveyId") Long surveyId, @AuthenticationPrincipal User user) {
        List<SurveyUserReplyResponse> reply = surveyReplyService.findUserReply(surveyId,
            user.getId());

        return ResponseEntity.ok(reply);
    }

    @PostMapping()
    @UserAuth
    @SecurityRequirement(name = "JWT Token")
    public void createReply(@RequestBody List<SurveyReplyCreateRequest> survey,
        AppAuthentication auth) {
        surveyReplyService.createReply(survey, auth.getUserId());
    }

    @PatchMapping("/{surveyId}")
    public void updateReply(@RequestBody List<SurveyReplyUpdateRequest> survey) {

        surveyReplyService.updateReply(survey);
    }

    @DeleteMapping("/{surveyId}")
    public void deleteReply(@PathVariable(name = "surveyId") Long surveyId,
        AppAuthentication auth) {
        surveyReplyService.deleteReply(surveyId, auth.getUserId());
    }
}
