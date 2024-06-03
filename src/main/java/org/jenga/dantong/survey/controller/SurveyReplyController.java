package org.jenga.dantong.survey.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @UserAuth
    @GetMapping("/user/{surveyId}")
    public ResponseEntity<List<SurveyUserReplyResponse>> findUserReply(@PathVariable("surveyId") Long surveyId, @AuthenticationPrincipal User user) {

        List<SurveyUserReplyResponse> reply = surveyReplyService.findUserReply(surveyId, user.getId());

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
