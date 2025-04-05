package longlh.dev.base.excel.base.excel.utils;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.regex.Pattern;

public class ExcelUtils {
    public static CellStyle getCommonCellStyle(SXSSFWorkbook workbook,
                                               Boolean needBorder,
                                               Boolean isBanner,
                                               Boolean isHeader,
                                               String value) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        if (!StringPool.nonNullOrEmpty(value) || !isValidFormat(value, "[0-9]+") && !isValidFormat(value, "[0-9,.]+")) {
            if (!StringPool.nonNullOrEmpty(value) || !isValidFormat(value, "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$") && !isValidFormat(value, "^\\d{2}/\\d{2}/\\d{4}$")) {
                if (StringPool.nonNullOrEmpty(value)) {
                    cellStyle.setAlignment(HorizontalAlignment.LEFT);
                }
            } else {
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
            }
        } else {
            cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        }

        Font font = workbook.createFont();
        font.setItalic(false);

        if (!isBanner) {
            font.setFontHeight((short) 240);
        } else {
            font.setFontHeight((short) 400);
            font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
            font.setBold(Boolean.TRUE);
        }

        if (isHeader) {
            cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.TAN.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
        }
        font.setFontName("Times New Roman");
        cellStyle.setFont(font);
        if (needBorder) {
            getBorderStyle(cellStyle);
        }
        cellStyle.setWrapText(true);
        return cellStyle;
    }

//    public static List<String> convertObjectToListString(Object dtoClazzInput) throws IllegalAccessException {
//        List<String> result = new ArrayList<>();
//        Field[] fields = dtoClazzInput.getClass().getDeclaredFields();
//        fieldArraySort(fields);
//        Field[] var3 = fields;
//        int var4 = fields.length;
//        for (int var5 = 0; var5 < var4; ++var5) {
//            Field = var3[var5];
//            f.setAccessible(true);
//            Annotation[] annotations = f.getDeclaredAnnotations();
//            Object value = f.get(dtoClazzInput);
//        }
//        Annotation[] var9 = annotations;
//        int var10 annotations.length;
//        for (int var11 = 0; var11 < var10; ++var11) {
//            Annotation anno = var9[var11];
//            if (anno instanceof Header && Objects.nonNull(value)) {
//                result.add(String.valueOf(value));
//            }
//            return result;
//        }
//    }


//    public static void fieldArraySort(Field[] fields) {
//        Arrays.sort(fields, (field1, field2) -> {
//            Header annotation1 = (Header) field1.getAnnotation(Header.class);
//            Header annotation2 = (Header) field2.getAnnotation(Header.class);
//            int index1 = Objects.nonNull(annotation1) ? annotation1.index() : 2147483647;
//            int index2 = Objects.nonNull(annotation2) ? annotation2.index() : 2147483647;
//            return Integer.compare(index1, index2);
//        });
//    }


    public static boolean isValidFormat(String date, String regex) {
        return Pattern.matches(regex, date);
    }

    public static void getBorderStyle(CellStyle cellStyle) {
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
    }


//    private boolean isEmptyAllCell(Row checkRow, Set<Integer> excludeColumnIndexes) {
//        for (Cell cell : checkRow) {
//            int columnIndex = cell.getColumnIndex();
//            String value = ExcelUtils.readCellContent(cell);
//            if (!FileUtil.is(value) && !excludeColumnIndexes.contains(columnIndex)) {
//                return false;
//            }
//        }
//        return true;
//    }


}
