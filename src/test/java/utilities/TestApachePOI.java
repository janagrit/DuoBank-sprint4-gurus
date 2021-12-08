package utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestApachePOI {

    public static void main(String[] args) throws IOException {

        // Workbook -> Sheet -> Row -> Cell
        // HSSF versions work with older file types (doc, xls)-> pre 2003
        // XSSF versions work with newer file types (docx, xlsx)-> after 2003

//        XSSFWorkbook workbook;
//        XSSFSheet sheet;
//        XSSFRow row;
//        XSSFCell cell;


        FileInputStream fis = new FileInputStream("testDataEcommerce.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        XSSFRow row = sheet.getRow(1);  // Apache POI method indexes are zero-based
        XSSFCell cell = row.getCell(1);

        System.out.println(cell);

        // Get no of rows
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        int lastRowNum = sheet.getLastRowNum();

        System.out.println(physicalNumberOfRows);
        System.out.println(lastRowNum);


        XSSFRow columnRow = sheet.getRow(0);
        int physicalNumberOfCells = columnRow.getPhysicalNumberOfCells();

        System.out.println(physicalNumberOfCells);


        for (int i = 0; i < physicalNumberOfRows; i++) {

            for (int j = 0; j < physicalNumberOfCells; j++) {
                System.out.print( sheet.getRow(i).getCell(j) + "\t") ;
            }
            System.out.println();

        }

        XSSFCell cellTestStatus = sheet.getRow(1).getCell(6);

        cellTestStatus.setCellValue("IABA");



        FileOutputStream fos = new FileOutputStream("testDataEcommerce.xlsx");

        workbook.write(fos);

        ExcelUtils excelUtils = new ExcelUtils("testDataEcommerce.xlsx", "Sheet1");

        System.out.println(excelUtils.getCellData(2, 1));


        String[][] dataAs2DArray = excelUtils.getDataAs2DArray();

        System.out.println(Arrays.deepToString(dataAs2DArray));

        List<Map<String, String>> dataAsListOfMaps = excelUtils.getDataAsListOfMaps();


        for (Map<String, String> dataAsListOfMap : dataAsListOfMaps) {
            System.out.println(dataAsListOfMap);
        }

        excelUtils.setCellData("Bitcoin", "Status", 1);


    }
}
