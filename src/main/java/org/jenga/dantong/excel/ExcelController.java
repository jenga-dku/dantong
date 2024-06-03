package org.jenga.dantong.excel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/excel")
public class ExcelController {

    private final ExcelService excelService;

    private final ExcelCreateService excelCreateService;

    @Value("${file.path}")
    private String filePath;

    @GetMapping("/poi/async")
    public ResponseEntity<String> downloadPoiExcelAsyncVersion(
        @RequestParam(name = "fileName") String fileName,
        @RequestParam(name = "surveyId") Long surveyId,
        HttpServletRequest request) {
        try {
            SXSSFWorkbook workbook = getWorkbook(surveyId);
            createExcel(workbook, fileName, request);
        } catch (IOException e) {
            log.error("[downloadPoiExcelAsyncVersion] {}", e.getMessage());
        }

        return ResponseEntity.ok("ok");
    }

    private SXSSFWorkbook getWorkbook(Long surveyId) throws IOException {
        Map<String, Object> map = excelCreateService.getExcelMap(surveyId);
        SXSSFWorkbook workbook = excelService.getWorkbook(map, surveyId);

        if (ObjectUtils.isEmpty(workbook)) {
            workbook = new SXSSFWorkbook();
        }

        return workbook;
    }

    private void createExcel(SXSSFWorkbook workbook
        , String fileName
        , HttpServletRequest request) throws IOException {
        String excelFilePath = filePath
            + fileName
            + ".xlsx";
        String tempFilePath = filePath
            + UUID.randomUUID().toString().substring(0, 8)
            + ".xlsx";

        try {
            //Write the workbook in file system
            Files.createDirectories(Paths.get(filePath));

            try (
                FileOutputStream out = new FileOutputStream(tempFilePath);
            ) {
                workbook.write(out);
            } catch (Exception e) {
                log.error("[ExampleApiController.createExcel ERROR] {}", e.getMessage());
            }

            saveFilePath(request, excelFilePath);
        } catch (Exception e) {
            log.error("[ExampleApiController.createExcel ERROR] {}", e.getMessage());
        } finally {
            File tempFile = new File(tempFilePath);
            File excelFile = new File(excelFilePath);
            tempFile.renameTo(excelFile);

            if (ObjectUtils.isNotEmpty(workbook)) {
                workbook.close();
            }
        }
    }

    private void saveFilePath(HttpServletRequest request, String filePath) {
        HttpSession session = request.getSession();
        Set<String> filePaths = (Set<String>) session.getAttribute(ExcelConstants.SESSION_KEY);

        if (ObjectUtils.isEmpty(filePaths)) {
            filePaths = new HashSet<>();
        }

        filePaths.add(filePath);
        session.setAttribute(ExcelConstants.SESSION_KEY, filePaths);
    }
}
