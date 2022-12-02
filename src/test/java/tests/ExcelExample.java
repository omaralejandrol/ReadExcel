package tests;

import com.beust.jcommander.Strings;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
public class ExcelExample {
    static String filePath = "src/test/resources/Excel/";
    static String fileName = "NombreEjemplo.xlsx";

    public void WriteExcelFile(String filePath, String fileName, String sheetName, String[] dataToWrite) throws IOException {
        //Excel file creation
        File file = new File(filePath+"\\"+fileName);
        //Create an FileInputStream object
        FileInputStream inputStream = new FileInputStream(file);
        Workbook ejemplo = null;
        ejemplo = new XSSFWorkbook(inputStream);

        //Reading the sheets by name
        Sheet sheet = ejemplo.getSheet(sheetName);
        //Getting the number of rows in it
        int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
        //Get the first row
        Row row = sheet.getRow(0);
        //Create a new row and append it at the end of the sheet.
        Row newRow = sheet.createRow(rowCount+1);
        //Loop to create cells
        for(int j = 0; j < row.getLastCellNum(); j++){
            //Fill data in row
            Cell cell = newRow.createCell(j);
            cell.setCellValue(dataToWrite[j]);

        }
        //Close inputStream
        inputStream.close();

        //Create an object of FileOutputStream to create data in the excel file
        FileOutputStream outputStream = new FileOutputStream(file);
        //Write the data in it
        ejemplo.write(outputStream);
        //Close OutputStream
        outputStream.close();
    }

    public void ReadExcelFile(String filePath, String fileName, String sheetName) throws IOException{
        //Create an object of File class to open xlsx file
        File file = new File(filePath+"\\"+fileName);
        //Create an object of FileInputStream class to read the excel file
        FileInputStream inputStream = new FileInputStream(file);
        Workbook ejemplo = null;
        ejemplo = new XSSFWorkbook(inputStream);

        //Read the sheet inside the workbook by its name
        Sheet ejemploSheet = ejemplo.getSheet(sheetName);
        //Find the number of rows in the excel file
        int rowCount = ejemploSheet.getLastRowNum()-ejemploSheet.getFirstRowNum();

        //Create a loop to read all the rows in the excel file
        for(int i = 0; i<rowCount+1; i++){
            Row row = ejemploSheet.getRow(i);
                //Create a loop to print the cell values in a row
                for(int j = 0; j<row.getLastCellNum(); j++){
                    //Display the excel data in console
                    System.out.print(row.getCell(j).getStringCellValue()+" || ");
                }
                    System.out.println();
        }
    }

    public static void main(String...strings) throws IOException{
        //Array with the data in same order as we are expecting
        String[] valueToWrite = {"Omar", "z"};
        //Create an objet of current class
        ExcelExample objExcelFile = new ExcelExample();
        //Write the file using the file name, sheet name and the data to be filled
        objExcelFile.WriteExcelFile(filePath, fileName,"sheetName", valueToWrite);
        //----------------------------------------------//
        //Read excel file
        objExcelFile.ReadExcelFile(filePath,fileName,"sheetName");

    }
}
