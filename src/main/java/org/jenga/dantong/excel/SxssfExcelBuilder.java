package org.jenga.dantong.excel;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

@Slf4j
public class SxssfExcelBuilder {

    private static final String SHEET_NAME = "Sheet";

    private static final String LEFT = "LEFT";

    private static final String CENTER = "CENTER";

    private static final String RIGHT = "RIGHT";

    private static final int MAX_ROW = 1040000;

    private static final int FLUSH_ROW_NUM = 100;

    public static SXSSFWorkbook createExcel(List<String> headers
        , List<String> keys
        , List<String> widths
        , String sheetName
        , List<Map<String, Object>> list
        , int rowIdx
        , SXSSFWorkbook sxssfWorkbook) throws IOException {

        SXSSFWorkbook workbook = ObjectUtils.isNotEmpty(sxssfWorkbook)
            ? sxssfWorkbook : new SXSSFWorkbook(-1);

        sheetName = StringUtils.isEmpty(sheetName)
            ? SHEET_NAME + (rowIdx / MAX_ROW + 1) : sheetName;
        boolean newSheet = ObjectUtils.isEmpty(workbook.getSheet(sheetName));
        Sheet sheet = newSheet ? workbook.createSheet(sheetName) : workbook.getSheet(sheetName);

        Row row = null;
        Cell cell = null;

        int rowNo = rowIdx % MAX_ROW;
        int index = 0;

        CellStyle headStyle = makeHeadStyle(workbook);
        CellStyle bodyStyleCenter = makeBodyStyle(workbook, CENTER);
        CellStyle bodyStyleLeft = makeBodyStyle(workbook, LEFT);
        CellStyle bodyStyleRight = makeBodyStyle(workbook, RIGHT);

        bodyStyleCenter.setWrapText(true);
        bodyStyleLeft.setWrapText(true);
        bodyStyleRight.setWrapText(true);

        if (ObjectUtils.isNotEmpty(widths)) {
            for (String width : widths) {
                sheet.setColumnWidth(index++, Integer.parseInt(width) * 256);
            }
        }

        // 헤더 생성
        if (newSheet) {
            row = sheet.createRow(rowNo);

            index = 0;

            for (String colName : headers) {
                cell = row.createCell(index++);
                cell.setCellStyle(headStyle);
                cell.setCellValue(colName);
            }
        }

        // 데이터와 cell alignment
        for (Map<String, Object> aRow : list) {
            row = sheet.createRow(++rowNo);
            index = 0;

            for (String aKey : keys) {
                if (StringUtils.isEmpty(aKey)) {
                    continue;
                }

                cell = row.createCell(index);
                cell.setCellStyle(bodyStyleCenter);

                Object aValue = aRow.get(aKey);

                if (aValue instanceof BigDecimal) {
                    cell.setCellValue(((BigDecimal) aValue).toString());
                } else if (aValue instanceof Double) {
                    cell.setCellValue(((Double) aValue).toString());
                } else if (aValue instanceof Long) {
                    cell.setCellValue(((Long) aValue).toString());
                } else if (aValue instanceof Integer) {
                    cell.setCellValue(((Integer) aValue).toString());
                } else if (aValue instanceof String[]) {
                    String[] options = (String[]) aValue;
                    DataValidationConstraint constraint = sheet.getDataValidationHelper()
                        .createExplicitListConstraint(options);
                    // firstRow, lastRow, firstCol, lastCol
                    CellRangeAddressList addressList = new CellRangeAddressList(rowNo, rowNo, index,
                        index);
                    DataValidation dataValidation = sheet.getDataValidationHelper()
                        .createValidation(constraint, addressList);
                    dataValidation.setSuppressDropDownArrow(true);
                    sheet.addValidationData(dataValidation);

                    cell.setCellValue(ObjectUtils.isNotEmpty(options) ? options[0] : null);
                } else {
                    cell.setCellValue((String) aValue);
                }

                index++;

                // 주기적인 flush 진행
                if (rowNo % FLUSH_ROW_NUM == 0) {
                    ((SXSSFSheet) sheet).flushRows(FLUSH_ROW_NUM);
                }
            }
        }

        return workbook;
    }

    public static CellStyle makeHeadStyle(SXSSFWorkbook workbook) {
        CellStyle headStyle = makeBodyStyle(workbook);
        headStyle.setFillForegroundColor(HSSFColor
            .HSSFColorPredefined
            .PALE_BLUE
            .getIndex());
        headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headStyle.setAlignment(HorizontalAlignment.CENTER);
        headStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        return headStyle;
    }

    public static CellStyle makeBodyStyle(SXSSFWorkbook workbook) {
        CellStyle bodyStyle = workbook.createCellStyle();
        bodyStyle.setBorderTop(BorderStyle.THIN);
        bodyStyle.setBorderBottom(BorderStyle.THIN);
        bodyStyle.setBorderLeft(BorderStyle.THIN);
        bodyStyle.setBorderRight(BorderStyle.THIN);
        bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        bodyStyle.setAlignment(HorizontalAlignment.CENTER);

        return bodyStyle;
    }

    public static CellStyle makeBodyStyle(SXSSFWorkbook workbook, String align) {
        CellStyle bodyStyle = workbook.createCellStyle();
        bodyStyle.setBorderTop(BorderStyle.THIN);
        bodyStyle.setBorderBottom(BorderStyle.THIN);
        bodyStyle.setBorderLeft(BorderStyle.THIN);
        bodyStyle.setBorderRight(BorderStyle.THIN);
        bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        if (StringUtils.isNotEmpty(align)) {
            if (LEFT.equals(align)) {
                bodyStyle.setAlignment(HorizontalAlignment.LEFT);
            } else if (RIGHT.equals(align)) {
                bodyStyle.setAlignment(HorizontalAlignment.RIGHT);
            } else {
                bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            }
        }

        return bodyStyle;
    }
}