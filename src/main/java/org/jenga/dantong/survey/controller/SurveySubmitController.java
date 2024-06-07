package org.jenga.dantong.survey.controller;

import lombok.RequiredArgsConstructor;
import org.jenga.dantong.global.auth.jwt.AppAuthentication;
import org.jenga.dantong.global.base.UserAuth;
import org.jenga.dantong.survey.model.dto.request.SurveySubmitCreateRequest;
import org.jenga.dantong.survey.service.SurveySubmitService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/submit")
public class SurveySubmitController {

    private final SurveySubmitService surveySubmitService;

    @PostMapping()
    @UserAuth
    public void createReply(@RequestBody SurveySubmitCreateRequest request,
        AppAuthentication auth) {
        surveySubmitService.createSubmit(request, auth.getUserId());
    }

    @DeleteMapping("/{submitId}")
    @UserAuth
    public void deleteReply(@PathVariable(name = "submitId") Long submitId) {
        
    }
}
