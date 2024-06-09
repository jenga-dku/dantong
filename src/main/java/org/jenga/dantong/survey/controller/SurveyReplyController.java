package org.jenga.dantong.survey.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.global.auth.jwt.AppAuthentication;
import org.jenga.dantong.global.base.UserAuth;
import org.jenga.dantong.survey.model.dto.request.SurveyReplyUpdateRequest;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/reply")
@RequiredArgsConstructor
@RestController
public class SurveyReplyController {

    private final SurveyReplyService surveyReplyService;
    private final SurveySubmitService surveySubmitService;

    @GetMapping("/{surveyItemId}")
    public ResponseEntity<List<SurveyUserReplyResponse>> findReply(
        @PathVariable("surveyItemId") Long surveyItemId) {
        List<SurveyUserReplyResponse> reply = surveyReplyService.findAllReplyBySurveyItem(
            surveyItemId);

        return ResponseEntity.ok(reply);
    }

    /**
     * 질문 항목별 응답 리스트 나열
     *
     * @param surveyId 설문 번호
     * @return
     */
    @GetMapping("/all/{surveyId}")
    public ResponseEntity<List<List<SurveyUserReplyResponse>>> findAllReply(
        @PathVariable("surveyId") Long surveyId) {
        List<List<SurveyUserReplyResponse>> replies = surveyReplyService.findAllReplyBySurvey(
            surveyId);
        return ResponseEntity.ok(replies);
    }

    @GetMapping("/user/{surveyId}")
    @UserAuth
    public ResponseEntity<List<SurveyUserReplyResponse>> findUserReply(
        @PathVariable("surveyId") Long surveyId, AppAuthentication user) {
        List<SurveyUserReplyResponse> reply = surveyReplyService.findUserReply(surveyId,
            user.getUserId());

        return ResponseEntity.ok(reply);
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
