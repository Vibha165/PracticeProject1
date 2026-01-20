package Utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders
{
    @DataProvider(name="LoginData")
    public String[][] getLoginData() throws IOException {
        String file_path="/Users/exly/Desktop/Exly/PracticeProject1/testData/vibha_login_datadriven_testing.xlsx";
        ExcelUtils xlutil= new ExcelUtils(file_path);

        int total_rows=xlutil.getRowCount("Sheet2");
        int total_col= xlutil.getCellCount("Sheet2",1);

        String login_data[][] = new String[total_rows][total_col];  // creating two-dimensional array
        for(int i=1;i<=total_rows;i++)
        {
            for(int j=0;j<total_col;j++)
            {
               login_data[i-1][j]= xlutil.getCellData("Sheet2",i,j);
            }

        }
        return login_data;  // returning two-dimensional array

    }
}
