package config;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ExcelFileReader {

    private static final Map<String, Workbook> workbookMap = new HashMap<String, Workbook>();

    public static String getCellValue(String fileName, String sheetName, int rowNum, String columnName) throws FileNotFoundException {
        Workbook workbook = workbookMap.get("fileName");
        FileInputStream fis = new FileInputStream("./src/test/resources/Data/"+ fileName);
        try {
            if (workbook == null) {
                workbook = WorkbookFactory.create(fis);
                workbookMap.put(fileName, workbook);
            }
            Sheet sheet = workbook.getSheet(sheetName);
            Cell cell = sheet.getRow(rowNum - 1).getCell(CellReference.convertColStringToIndex(columnName));
            return cell.getStringCellValue();
        } catch (Exception e) {
            return "";
        }
    }

    public static void writeToCell(String fileName, String sheetName, int rowNumber, int columnName, String firstvalue) throws IOException {

        FileInputStream fis = new FileInputStream("./src/test/resources/Data/"+ fileName);
        Workbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheet(sheetName);
        sheet.getRow(rowNumber).createCell(columnName).removeCellComment();
        sheet.getRow(rowNumber).createCell(columnName).setCellValue(firstvalue);
        FileOutputStream fout=new FileOutputStream("./src/test/resources/Data/"+ fileName);
        wb.write(fout);
        fout.close();
    }

}
