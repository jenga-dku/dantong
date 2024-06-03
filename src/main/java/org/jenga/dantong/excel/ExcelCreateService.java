package org.jenga.dantong.excel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.survey.exception.SurveyNotFoundException;
import org.jenga.dantong.survey.model.entity.Survey;
import org.jenga.dantong.survey.model.entity.SurveyItem;
import org.jenga.dantong.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelCreateService {

    private final static int PAGE_SIZE = 10000;
    private final SurveyRepository surveyRepository;

    public Map<String, Object> getExcelMap(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
            .orElseThrow(SurveyNotFoundException::new);

        List<String> keys = Arrays.asList("TIMESTAMP"
            , "COLUMN_1"
            , "COLUMN_2"
            , "COLUMN_3"
            , "COLUMN_4"
            , "COLUMN_5"
            , "COLUMN_6");
        List<String> headers = survey.getSurveyItems().stream()
            .map(SurveyItem::getTitle)
            .collect(Collectors.toList());

        Map<String, Object> map = new HashMap<>();
        map.put(ExcelConstants.HEADERS, headers);
        map.put(ExcelConstants.KEYS, keys);
        map.put(ExcelConstants.LIST_SIZE, 10000);

        return map;
    }

    public List<Map<String, Object>> getListForPoi(int start, int size) {
        List<Map<String, Object>> list = new ArrayList<>();

        ExampleVO vo = new ExampleVO(1L, "1", "2", 1, 1L, 1.0, "1");
        List<ExampleVO> vos = List.of(vo);
        for (ExampleVO exampleVO : vos) {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("NO", exampleVO.getId());
            tempMap.put("COLUMN_1", exampleVO.getColumn1());
            tempMap.put("COLUMN_2", exampleVO.getColumn2());
            tempMap.put("COLUMN_3", exampleVO.getColumn3());
            tempMap.put("COLUMN_4", exampleVO.getColumn4());
            tempMap.put("COLUMN_5", exampleVO.getColumn5());
            tempMap.put("COLUMN_6", exampleVO.getColumn6());

            list.add(tempMap);
        }

        return list;
    }
}