package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.AddEmpPage;
import config.AppUtilPom;
import utilities.ExcelFileUtil;

public class TestScript extends AppUtilPom{
	String inputpath = "H:\\Software_Testing_Course\\Selenium_Scripts\\DDT_FrameWork\\FileInput\\Employee.xlsx";
	String outputpath = "H:\\Software_Testing_Course\\Selenium_Scripts\\DDT_FrameWork\\FileOutput\\EmployeeResults.xlsx";
	@Test
	public void startTest() throws Throwable
	{
		AddEmpPage emp = PageFactory.initElements(driver, AddEmpPage.class);
		//create object for Excelfile util
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count no of rows in EmpData Sheet
		int rc = xl.rowCount("EmpData");
		Reporter.log("No of Rows in the sheet are ::"+rc, true);
		for (int i=1;i<=rc;i++)
		{
			String FirstName = xl.getCellData("EmpData", i, 0);
			String MiddleName = xl.getCellData("EmpData", i, 1);
			String LastName = xl.getCellData("EmpData", i, 2);
			boolean res = emp.verify_Emp(FirstName, MiddleName, LastName);
			if(res)
			{
				//write pass into status cell
				xl.setCellData("EmpData", i, 3, "Pass", outputpath);
			}
			else
			{
				xl.setCellData("EmpData", i, 3, "Fail", outputpath);
			}

		}

	}
}
