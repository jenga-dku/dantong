package org.jenga.dantong.survey.controller;

import lombok.RequiredArgsConstructor;
import org.jenga.dantong.global.auth.jwt.AppAuthentication;
import org.jenga.dantong.global.base.UserAuth;
import org.jenga.dantong.survey.model.dto.request.SurveySubmitCreateRequest;
import org.jenga.dantong.survey.model.dto.response.SurveySubmitResponse;
import org.jenga.dantong.survey.service.SurveySubmitService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/submit")
public class SurveySubmitController {

    private final SurveySubmitService surveySubmitService;

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
