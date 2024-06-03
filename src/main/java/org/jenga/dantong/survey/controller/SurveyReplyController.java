package org.jenga.dantong.survey.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/reply")
@RequiredArgsConstructor
@RestController
public class SurveyReplyController {

    private final SurveyReplyService surveyReplyService;

    @GetMapping("/{surveyId}")
    public ResponseEntity<List<List<SurveyUserReplyResponse>>> findAllReply(@PathVariable("surveyId") Long surveyId) {
        List<List<SurveyUserReplyResponse>> reply = surveyReplyService.findAllReply(surveyId);

        return ResponseEntity.ok(reply);
    }

    @GetMapping("/user/{surveyId}")
    @UserAuth
    @SecurityRequirement(name = "JWT Token")
    public ResponseEntity<List<SurveyUserReplyResponse>> findUserReply(@PathVariable("surveyId") Long surveyId, AppAuthentication user) {

        List<SurveyUserReplyResponse> reply = surveyReplyService.findUserReply(surveyId, user.getUserId());

        return ResponseEntity.ok(reply);
    }

    @PostMapping()
    public void createReply(@RequestBody List<SurveyReplyCreateRequest> survey) {

        surveyReplyService.createReply(survey);
    }

    @PatchMapping("/{surveyId}")
    public void updateReply(@RequestBody List<SurveyReplyUpdateRequest> survey) {

        surveyReplyService.updateReply(survey);
    }

    @DeleteMapping("/{surveyId}")
    public void deleteUserReply(@PathVariable(name = "surveyId") Long surveyId, @AuthenticationPrincipal User user) {

        surveyReplyService.deleteUserReply(surveyId, user.getId());
    }
}
