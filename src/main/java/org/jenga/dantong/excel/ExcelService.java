package org.jenga.dantong.excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.jenga.dantong.survey.exception.SurveyNotFoundException;
import org.jenga.dantong.survey.model.entity.Survey;
import org.jenga.dantong.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelService {

    private final ExcelCreateService excelCreateService;
    private final SurveyRepository surveyRepository;
    private static final int PAGE_SIZE = 10000;

    public SXSSFWorkbook getWorkbook(Map<String, Object> excelMap, Long surveyId)
        throws IOException {
        List<String> keys = (List<String>) excelMap.get(ExcelConstants.KEYS);
        List<String> headers = (List<String>) excelMap.get(ExcelConstants.HEADERS);
        int listSize = (int) excelMap.get(ExcelConstants.LIST_SIZE);

        Survey survey = surveyRepository.findById(surveyId)
            .orElseThrow(SurveyNotFoundException::new);
        SXSSFWorkbook sxssfWorkbook = null;

        try {
            for (int start = 0; start < listSize; start += PAGE_SIZE) {
                List<Map<String, Object>> list = getExcelList(start, PAGE_SIZE, survey);
                sxssfWorkbook = SxssfExcelBuilder.createExcel(headers
                    , keys
                    , null
                    , null
                    , list
                    , start
                    , start == 0 ? null : sxssfWorkbook);

                list.clear();
            }
        } catch (Exception e) {
            log.error("[SxssfExcelService] error message: {}", e.getMessage());
        }

        if (listSize == 0) {
            sxssfWorkbook = SxssfExcelBuilder.createExcel(headers
                , keys
                , null
                , null
                , new ArrayList<>()
                , 0
                , null);
        }

        return sxssfWorkbook;
    }

    public List<Map<String, Object>> getExcelList(int start
        , int size, Survey survey) {
        return excelCreateService.getListForPoi(start, size, survey);

    }

}
