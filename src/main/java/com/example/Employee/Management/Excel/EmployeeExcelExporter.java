package com.example.Employee.Management.Excel;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



import com.example.Employee.Management.entities.EmployeeDetails;




public class EmployeeExcelExporter {

	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<EmployeeDetails> listEmployee;
    
    public EmployeeExcelExporter(List<EmployeeDetails> listEmployee) {
        this.listEmployee = listEmployee;
        workbook = new XSSFWorkbook();
    }
    
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if(value instanceof Long) {
        	cell.setCellValue((Long) value);
        }else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
    
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Employee Details");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "ID", style);      
        createCell(row, 1, "Name", style);       
        createCell(row, 2, "EMAIL ID", style);    
        createCell(row, 3, "ADDRESS", style);
        createCell(row, 4, "MOBILE NUMBER", style);
        createCell(row, 5, "GENDER", style);
        createCell(row, 6, "PROGRAMMING LANGUAGE", style);
        createCell(row, 7, "TEAM", style);
         
    }
    
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (EmployeeDetails employee : listEmployee) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, employee.getId(), style);
            createCell(row, columnCount++, employee.getName(), style);
            createCell(row, columnCount++, employee.getEmailId(), style);
            createCell(row, columnCount++, employee.getAddress(), style);
            createCell(row, columnCount++, employee.getMobileNumber(), style);
            createCell(row, columnCount++, employee.getGender().toString(), style);  
            createCell(row, columnCount++, employee.getProgrammingLanguage(), style);
            createCell(row, columnCount++, employee.getTeam().toString(), style);
        }
    }
    
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }

}
