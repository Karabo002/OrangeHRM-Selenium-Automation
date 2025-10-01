package utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelUtils(String filePath, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not read Excel file");
        }
    }

    public String getCellData(int row, int col) {
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(sheet.getRow(row).getCell(col));
    }

    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    public int getColumnCount() {
        return sheet.getRow(0).getPhysicalNumberOfCells();
    }

    public String[] getRowData(int rowIndex) {
        Row row = sheet.getRow(rowIndex);
        String[] data = new String[row.getPhysicalNumberOfCells()];
        for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
            data[i] = new DataFormatter().formatCellValue(row.getCell(i));
        }
        return data;
    }
}