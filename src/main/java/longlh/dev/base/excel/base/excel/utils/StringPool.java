package longlh.dev.base.excel.base.excel.utils;

public class StringPool {
    public static final String BLANK = "";

    public static boolean nonNullOrEmpty(String obj) {
        return obj != null && !obj.trim().isEmpty();
    }
}
