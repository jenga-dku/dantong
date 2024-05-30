package org.jenga.dantong.excel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelCreateService {

    private final static int PAGE_SIZE = 10000;


    public Map<String, Object> getExcelMap() {
        List<String> keys = Arrays.asList("NO"
            , "COLUMN_1"
            , "COLUMN_2"
            , "COLUMN_3"
            , "COLUMN_4"
            , "COLUMN_5"
            , "COLUMN_6");
        List<String> headers = Arrays.asList("No."
            , "첫번 째 칼럼"
            , "두번 째 칼럼"
            , "세번 째 칼럼"
            , "네번 째 칼럼"
            , "다섯번 째 칼럼"
            , "여섯번 째 칼럼");
        List<String> widths = Arrays.asList("50"
            , "50"
            , "50"
            , "50"
            , "50"
            , "50"
            , "50");
        List<String> aligns = Arrays.asList("CENTER"
            , "CENTER"
            , "CENTER"
            , "CENTER"
            , "CENTER"
            , "CENTER"
            , "CENTER");

        Map<String, Object> map = new HashMap<>();
        map.put(ExcelConstants.HEADERS, headers);
        map.put(ExcelConstants.WIDTHS, widths);
        map.put(ExcelConstants.KEYS, keys);
        map.put(ExcelConstants.ALIGNS, aligns);
        map.put(ExcelConstants.LIST_SIZE, 10000);

        return map;
    }

    public List<Map<String, Object>> getListForFastExcel(int idx, int start) {
        ExampleVO vo = new ExampleVO(1L, "1", "2", 1, 1L, 1.0, "1");
        List<ExampleVO> vos = List.of(vo);
        List<Map<String, Object>> list = new ArrayList<>();

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