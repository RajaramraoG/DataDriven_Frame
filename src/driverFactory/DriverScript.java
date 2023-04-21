package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import config.AppUtil;
import driverFactory.FunctionLibrary;
import utilities.ExcelFileUtil;


public class DriverScript extends AppUtil {
	String inputpath = "H:\\Software_Testing_Course\\Selenium_Scripts\\DDT_FrameWork\\FileInput\\DDF_Login.xlsx";
	String outputpath = "H:\\Software_Testing_Course\\Selenium_Scripts\\DDT_FrameWork\\FileOutput\\DDF_Results.xlsx";
	ExtentReports report;
	ExtentTest test;
	@Test
	public void startTest()throws Throwable
	{
		//create object for excel file util
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count no of rows in login sheet
		int rc = xl.rowCount("Login");
		report = new ExtentReports("./ExtenReports/Login.html");
		Reporter.log("No of rows are::"+rc, true);
		for(int i =1;i<=rc;i++)
		{
			test = report.startTest("Validate Login");
			test.assignAuthor("Rajaram");
			//read username and password cell data
			String username =xl.getCellData("Login", i, 0);
			String password = xl.getCellData("Login", i, 1);
			//calling login method
			boolean res = FunctionLibrary.verify_Login(username, password);
			if(res)
			{
				// if res is true write as login success into results cell
				xl.setCellData("Login", i, 2, "Login Success", outputpath);
				xl.setCellData("Login", i, 3, "Pass", outputpath);
				test.log(LogStatus.PASS, "Login Success");
			}
			else
			{
				File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screen, new File("./Screenshot/iteration/"+i+"Loginpage.png"));
				//if res is false write as Login fail in results cell
				xl.setCellData("Login", i, 2, "Login Failed", outputpath);
				xl.setCellData("Login", i, 3, "Fail", outputpath);
				test.log(LogStatus.FAIL, "Login Fail");
			}
			report.endTest(test);
			report.flush();
		}
				
	}
}
