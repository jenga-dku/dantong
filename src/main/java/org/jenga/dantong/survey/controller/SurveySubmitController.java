package org.jenga.dantong.survey.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jenga.dantong.global.auth.jwt.AppAuthentication;
import org.jenga.dantong.global.base.UserAuth;
import org.jenga.dantong.survey.model.dto.request.SurveySubmitCreateRequest;
import org.jenga.dantong.survey.model.dto.response.SurveySubmitResponse;
import org.jenga.dantong.survey.service.SurveySubmitService;
import org.jenga.dantong.user.repository.UserRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    //    private final NotificationService notificationService;
    private final UserRepository userRepository;

    @PostMapping()
    @UserAuth
    public void createReply(@RequestBody @Validated SurveySubmitCreateRequest request,
        AppAuthentication auth) {
        surveySubmitService.createSubmit(request, auth.getUserId());
    }

    @DeleteMapping("/{submitId}")
    @UserAuth
    public void deleteReply(@PathVariable(name = "submitId") Long submitId,
        AppAuthentication auth) {
        surveySubmitService.deleteSubmit(submitId, auth.getUserId());
    }

    @GetMapping("/{submitId}")
    @UserAuth
    public SurveySubmitResponse submit(@PathVariable(name = "submitId") Long submitId,
        AppAuthentication auth) {
        return surveySubmitService.getSubmit(submitId, auth.getUserId());
    }

    @GetMapping("/user/{surveyId}")
    @UserAuth
    public SurveySubmitResponse userSubmit(@PathVariable(name = "surveyId") Long surveyId,
        AppAuthentication auth) {
        return surveySubmitService.getUserSubmit(surveyId, auth.getUserId());

    }

    /**
     * 설문에 대한 전체 응답결과를 조회하기
     *
     * @param surveyId
     * @return
     */
    @GetMapping("/survey/{surveyId}")
    public List<SurveySubmitResponse> getSubmissions(
        @PathVariable(name = "surveyId") Long surveyId) {
        return surveySubmitService.getSubmissions(surveyId);
    }
}
