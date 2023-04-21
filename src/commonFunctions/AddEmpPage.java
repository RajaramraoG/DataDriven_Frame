package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AddEmpPage {
	WebDriver driver;
	//constructor
	public AddEmpPage(WebDriver driver)
	{
		this.driver = driver;
	}
	//define repository
	@FindBy(xpath = "//b[normalize-space()='PIM']")
	WebElement ClickPim;
	@FindBy(xpath = "//input[@id='btnAdd']")
	WebElement ClickAddBtn;
	@FindBy(xpath ="//input[@id='firstName']")
	WebElement EnterFname;
	@FindBy(xpath = "//input[@id='middleName']")
	WebElement EnterMname;
	@FindBy(xpath = "//input[@id='lastName']")
	WebElement EnterLname;
	@FindBy(xpath = "//input[@id='employeeId']")
	WebElement BeforeEid;
	@FindBy(xpath = "//input[@id='btnSave']")
	WebElement ClickSaveBtn;
	@FindBy(xpath = "//input[@id='personal_txtEmployeeId']")
	WebElement AfterEid;
	public boolean verify_Emp(String fname, String mname, String lname)
	{
		Actions ac= new Actions(driver);
		ac.moveToElement(ClickPim).click().perform();
		ac.moveToElement(ClickAddBtn).click().perform();
		this.EnterFname.sendKeys(fname);
		this.EnterMname.sendKeys(mname);
		this.EnterLname.sendKeys(lname);
		//capture Employee ID to check if the process is success
		String ExpectedEid =this.BeforeEid.getAttribute("value");
		ac.moveToElement(this.ClickSaveBtn).click().perform();
		String ActualEid = this.AfterEid.getAttribute("value");
		if(ExpectedEid.equals(ActualEid))
		{
			Reporter.log("Add Employee Success::"+"   "+ExpectedEid+"   "+ActualEid);	
			return true;
		}
		else
		{
			Reporter.log("Add Employee Failed::"+"    "+ExpectedEid+"   "+ActualEid);
			return false;
		}

	}

}
