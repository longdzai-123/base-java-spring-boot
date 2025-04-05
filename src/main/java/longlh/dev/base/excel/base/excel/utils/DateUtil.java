package longlh.dev.base.excel.base.excel.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateUtil {
    public static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss";
    public static final String TIME_PATTERN = "HH:mm:ss";
    public static final String DATE_PATTERN = "dd/MM/yyyy";
    public static final String DATE_TIME_PATTERN_EXPORT = "yyyy_MM_dd_HH_mm_ss";

    public DateUtil() {
    }

    public static LocalDate toLocalDateFromString(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String toStringFromLocalDateTime(LocalDateTime localDateTime) {
        return Objects.nonNull(localDateTime) ? localDateTime.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
    }

    public static String toStringFromLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return Objects.nonNull(localDateTime) ? localDateTime.format(DateTimeFormatter.ofPattern(pattern)) : null;
    }
}
