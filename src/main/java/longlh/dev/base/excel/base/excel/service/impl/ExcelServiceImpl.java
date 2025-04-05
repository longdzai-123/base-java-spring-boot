package longlh.dev.base.excel.base.excel.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import longlh.dev.base.excel.base.excel.service.ExcelService;
import longlh.dev.base.excel.base.excel.utils.DateUtil;
import longlh.dev.base.excel.base.excel.utils.ExcelUtils;
import longlh.dev.base.excel.base.excel.utils.StringPool;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExcelServiceImpl implements ExcelService {
    @Override
    public void createExcel(HttpServletResponse response,
                            String fileName,
                            String headerName,
                            List<String> headerExport,
                            List<List<String>> result) throws IOException {
        // tao workbook
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        try {
            String resultFileName = setFileName(fileName);
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resultFileName);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            OutputStream os = response.getOutputStream();

            // tao trang excel (tao sheet) mac dinh ten la DATA
            SXSSFSheet sheet = workbook.createSheet("DATA");

            // tao tieu de + thoi gian xuat file
            Integer countColumn = headerExport.size();
            createHeaderAndTime(workbook, headerName, countColumn);

            sheet.trackColumnsForAutoSizing(IntStream.range(0, countColumn).boxed().collect(Collectors.toList()));

            //List<String> header = ExcelUtils.convertObjectToListString(headerExport);
            //khởi tạo dòng bắt đầu của bảng
            AtomicInteger rowNum = new AtomicInteger(3);
            AtomicLong count = new AtomicLong(1);
            //fill header
            Row row = sheet.createRow(rowNum.getAndIncrement());
            //ExcelUtils.createCell(workbook, row, header, Boolean.TRUE, OL);
            //fill data
            Map<Long, List<String>> listMap = new HashMap<>();
//            for (int i = 0; i < result.size(); i++) {
//                List<String> rowDataString = StringPool.convertArrayToList((Object[]) result.get(i));
//                if (!listMap.containsValue(rowDataString)) {
//                    Row rowData = sheet.createRow(rowNum.getAndIncrement());
//                    ExcelUtils.createCell(workbook, rowData, rowDataString, Boolean.FALSE, count.get());
//                    listMap.put(count.getAndIncrement(), rowDataString);
//                }
//            }

        } finally {
            workbook.close();
        }

    }

    private String setFileName(String fileName) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss:");
        return String.format(fileName, dateTimeFormatter.format(dateTime)) + ".xlsx";
    }

    private static void createHeaderAndTime(SXSSFWorkbook workbook, String headerName, Integer countColumn) {
        SXSSFSheet sheet = workbook.getSheetAt(0);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, countColumn - 1));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, countColumn - 1));
        SXSSFRow headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints((float) 30);
        SXSSFCell headerCell = headerRow.createCell(0);
        headerCell.setCellValue(headerName);
        headerCell.setCellStyle(ExcelUtils.getCommonCellStyle(workbook, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, StringPool.BLANK));

        SXSSFRow timeRow = sheet.createRow(1);
        SXSSFCell timeCell = timeRow.createCell(0);
        timeCell.setCellValue("Time export: " + DateUtil.toStringFromLocalDateTime(LocalDateTime.now(), DateUtil.DATE_TIME_PATTERN));
        timeCell.setCellStyle(ExcelUtils.getCommonCellStyle(workbook, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, StringPool.BLANK));
    }
}
