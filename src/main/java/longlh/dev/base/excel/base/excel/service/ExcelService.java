package longlh.dev.base.excel.base.excel.service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface ExcelService {
    void createExcel(HttpServletResponse httpServletResponse,
                     String fileName,
                     String headerName,
                     List<String> headerExport,
                     List<List<String>> result) throws IOException;
}
