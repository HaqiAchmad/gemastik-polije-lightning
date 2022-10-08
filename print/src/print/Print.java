package print;
// Java program to write data in excel sheet using java code 
  
import java.io.File; 
import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.xssf.usermodel.XSSFRow; 
import org.apache.poi.xssf.usermodel.XSSFSheet; 
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 
import java.io.FileOutputStream; 
import java.util.Map; 
import java.util.Set; 
import java.util.TreeMap; 
  
public class Print { 
  
    // any exceptions need to be caught 
    public static void main(String[] args) throws Exception 
    { 
        // workbook object 
        XSSFWorkbook workbook = new XSSFWorkbook("balance.xlsx");
        // spreadsheet object 
        XSSFSheet spreadsheet = workbook.createSheet(" Student Data "); 
  
        // creating a row object 
        XSSFRow row; 
  
        // This data needs to be written (Object[]) 
        Map<String, Object[]> studentData = new TreeMap<String, Object[]>(); 
  
        studentData.put( 
            "1", 
            new Object[] { "Roll No", "NAME", "Year" }); 
  
        studentData.put("2", new Object[] { "128", "Aditya", 
                                            "2nd year" }); 
  
        studentData.put( 
            "3", 
            new Object[] { "129", "Narayana", "2nd year" }); 
  
        studentData.put("4", new Object[] { "130", "Mohan", 
                                            "2nd year" }); 
  
        studentData.put("5", new Object[] { "131", "Radha", 
                                            "2nd year" }); 
  
        studentData.put("6", new Object[] { "132", "Gopal", 
                                            "2nd year" }); 
  
        Set<String> keyid = studentData.keySet(); 
  
        int rowid = 0; 
  
        // writing the data into the sheets... 
  
        for (String key : keyid) { 
  
            row = spreadsheet.createRow(rowid++); 
            Object[] objectArr = studentData.get(key); 
            int cellid = 0; 
  
            for (Object obj : objectArr) { 
                Cell cell = row.createCell(cellid++); 
                cell.setCellValue((String)obj); 
            } 
        } 
  
        // .xlsx is the format for Excel Sheets... 
        // writing the workbook into the file... 
        FileOutputStream out = new FileOutputStream( 
            new File("\\GFGsheet.xlsx")); 
  
        workbook.write(out); 
        out.close(); 
    } 
}




























/*import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.EmptyFileException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
*//*
import java.io.File; 
import org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.xssf.usermodel.XSSFRow; 
import org.apache.poi.xssf.usermodel.XSSFSheet; 
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 
import java.io.FileOutputStream; 
import java.util.Map; 
import java.util.Set; 
import java.util.TreeMap; 



public class Print {
    public static void main(String[] args) {
        int[] serial = new int [10];
        for(int i=0;i<serial.length;i++){
            serial[i] = i+1;
        }
        String[] name = new String[10];
        name[0] = "yanto";
        name[1] = "akvnk";
        name[2] = "asdvd";
        name[3] = "zvzvs";
        name[4] = "bsdac";
        name[5] = "sbbba";
        name[6] = "faaee";
        name[7] = "jvakk";
        name[8] = "jvakk";
        name[9] = "jvakk";
        String[] result = new String[10];
        result[0] = "pass";
        result[1] = "fail";
        result[2] = "pass";
        result[3] = "pass";
        result[4] = "fail";
        result[5] = "pass";
        result[6] = "fail";
        result[7] = "pass";
        result[8] = "fail";
        result[9] = "pass";
        //create workbook 
        XSSFWorkbook workbook = new XSSFWorkbook();
        //create spreadsheet
        XSSFSheet sheet = workbook.createSheet("lembar1");
        //create a row object 
        XSSFRow row;
        //create cell;
        row = sheet.createRow(0);
        Cell cell0 = row.createCell(0);
        Cell cell1 = row.createCell(1);
        Cell cell2 = row.createCell(2);
        //input data 
        cell0.setCellValue("Serial");
        cell1.setCellValue("name");
        cell2.setCellValue("result");
        for(int i=0;i<serial.length;i++){
            row = sheet.createRow(i+1);
            for(int j=0;j<3;j++){
                Cell cell = row.createCell(j);
                if(cell.getColumnIndex()== 0){
                    cell.setCellValue(serial[i]);
                }else if(cell.getColumnIndex()== 1){
                    cell.setCellValue(name[i]);
                }else if(cell.getColumnIndex()== 2){
                    cell.setCellValue(result[i]);
                }
            }
           
        }
        //create excel file 
        try{
            FileOutputStream out = new FileOutputStream(new File("H:\\Amirzan fikri P\\Java\\output\\output\\Result.xlsx"));
            workbook.write(out);
            out.close();
            System.out.print("Excel file created succesfully");
        }catch(FileNotFoundException ex){
            Logger.getLogger(ExportExcel.class.getName()).log(Level.SEVERE,null,ex);
        }catch(IOException ex){
            Logger.getLogger(ExportExcel.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
*/