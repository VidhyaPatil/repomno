package Tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObjects.Login;
import resource.BaseClass;

public class ShoppingTest extends BaseClass {
	public WebDriver driver;

	@BeforeMethod
	public void openBrowser() throws IOException {
		driver = initilization();
	}

	@Test
	public void shopping() {
		
		Login login = new Login(driver);
		login.userName().sendKeys("standard_user");
		login.password().sendKeys("secret_sauce");
		login.loginButton().click();

		logger.info("Browser open");
		//Assert.assertTrue(false);
	}

	@AfterMethod
	public void tearOff() {
		logger.info("Browser Closed...");
		driver.quit();
	}
}
