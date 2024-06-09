package org.jenga.dantong.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.survey.exception.SurveyNotFoundException;
import org.jenga.dantong.survey.model.entity.Survey;
import org.jenga.dantong.survey.model.entity.SurveyItem;
import org.jenga.dantong.survey.repository.SurveyItemRepository;
import org.jenga.dantong.survey.repository.SurveyRepository;
import org.jenga.dantong.survey.repository.SurveySubmitRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelCreateService {

    private final static int PAGE_SIZE = 10000;
    private final SurveyRepository surveyRepository;
    private final SurveyItemRepository surveyItemRepository;
    private final SurveySubmitRepository surveySubmitRepository;

    public Map<String, Object> getExcelMap(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
            .orElseThrow(SurveyNotFoundException::new);
        List<String> titles = survey.getSurveyItems().stream()
            .map(SurveyItem::getTitle)
            .collect(Collectors.toList());
        List<String> headers = new ArrayList<>(List.of("TIMESTAMP"));
        headers.addAll(titles);
        List<String> keys = new ArrayList<>(List.of("TIMESTAMP"));
        keys.addAll(titles);

        Map<String, Object> map = new HashMap<>();
        map.put(ExcelConstants.HEADERS, headers);
        map.put(ExcelConstants.KEYS, keys);
        map.put(ExcelConstants.LIST_SIZE, 10000);

        return map;
    }

    public List<Map<String, Object>> getListForPoi(int start, int size, Survey survey) {
        List<Map<String, Object>> list = new ArrayList<>();
        survey.getSurveySubmits().
            forEach(surveySubmit -> {
                Map<String, Object> tempMap = new HashMap<>();
                surveySubmit.getSurveyReplies().
                    forEach(surveyReply -> {
                        tempMap.put(surveyReply.getSurveyItem().getTitle(),
                            surveyReply.getContent());
                    });
                tempMap.put("TIMESTAMP", surveySubmit.getCreatedAt().toString());

                list.add(tempMap);
            });

        return list;
    }
}