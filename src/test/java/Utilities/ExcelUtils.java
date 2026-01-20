package Utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelUtils {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    String path;

    public ExcelUtils(String path)
    {
        this.path=path;
    }

    public int getRowCount(String sheet_name) throws IOException
    {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheet_name);
        int row_count=sheet.getLastRowNum();
        fi.close();
        workbook.close();
        return row_count;
    }

    public int getCellCount(String sheet_name, int row_num) throws IOException
    {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheet_name);
        int cell_count= sheet.getRow(row_num).getLastCellNum();
        fi.close();
        workbook.close();
        return cell_count;
    }

    public String getCellData(String sheet_name,int row_num,int cell_num) throws IOException
    {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheet_name);
        cell=sheet.getRow(row_num).getCell(cell_num);
        DataFormatter formatter=new DataFormatter();
        String data;
        try
        {
            data= formatter.formatCellValue(cell);
        }
        catch(Exception e)
        {
            data="";
        }
        fi.close();
        workbook.close();
        return data;

    }

    public void setCellData(String sheet_name,int row_num,int cell_num,String data) throws IOException {
        File xlfile =new File(path);
        if(!xlfile.exists())                          // if file not present, create one
        {
           workbook=new XSSFWorkbook();
           fo=new FileOutputStream(path);
           workbook.write(fo);
        }

        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);

        if(workbook.getSheetIndex(sheet_name)==-1)     // if sheet not present , create one
        {
            workbook.createSheet(sheet_name);
        }
        workbook.getSheet(sheet_name);

        if(sheet.getRow(row_num)==null)               // if row not present, create one
        {
            sheet.createRow(row_num);
        }
        sheet.getRow(row_num);

        cell=sheet.getRow(row_num).createCell(cell_num);
        cell.setCellValue(data);
        fo=new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }
}
