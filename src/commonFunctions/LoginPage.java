package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
//define repository for Login
	@FindBy(xpath = "//input[@id='txtUsername']")
	WebElement EnterUser;
	@FindBy(xpath = "//input[@id='txtPassword']")
	WebElement EnterPass;
	@FindBy(xpath = "//input[@id='btnLogin']")
	WebElement ClickLogin;
	@FindBy(xpath = "//span[@id='spanMessage']")
	WebElement ErrorMessage;
	//method for login
	public void verify_Login(String username, String password)
	{
		EnterUser.sendKeys(username);
		EnterPass.sendKeys(password);
		ClickLogin.click();
	}
}
