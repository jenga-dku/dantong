package org.jenga.dantong.survey.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.survey.model.dto.SurveyReplyCreateRequest;
import org.jenga.dantong.survey.model.dto.SurveyReplyResponse;
import org.jenga.dantong.survey.model.dto.SurveyReplyUpdateRequest;
import org.jenga.dantong.survey.service.SurveyReplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/reply")
@RequiredArgsConstructor
@RestController
public class SurveyReplyController {

    private final SurveyReplyService surveyReplyService;

    @GetMapping("/{surveyId}")
    public ResponseEntity<List<SurveyReplyResponse>> findReply(@PathVariable("surveyId") int surveyId) {

        List<SurveyReplyResponse> reply = surveyReplyService.findReply(surveyId);

        return ResponseEntity.ok(reply);
    }

    @PostMapping()
    public void createReply(@RequestBody List<SurveyReplyCreateRequest> survey) {

        surveyReplyService.createReply(survey);
    }

    @PatchMapping()
    public void updateReply(@RequestBody List<SurveyReplyUpdateRequest> survey) {

        surveyReplyService.updateReply(survey);
    }

    @DeleteMapping("/{surveyId}")
    public void deleteReply(@PathVariable(name = "surveyId") int surveyId) {

        surveyReplyService.deleteReply(surveyId);
    }
}
