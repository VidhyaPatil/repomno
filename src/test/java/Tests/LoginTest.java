package Tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.Login;
import PageObjects.ProductPage;
import resource.BaseClass;

public class LoginTest extends BaseClass {
	public WebDriver driver;
	String actualResult = null ;
	@BeforeMethod
	public void openBrowser() throws IOException {
		driver = initilization();
		// driver.get(prop.getProperty("URL"));
		logger.info("Browser open.....");
	}

	@Test(dataProvider = "Data")
	public void login(String userName, String password, String expectedResult) throws IOException, InterruptedException {

		Login login = new Login(driver);
		login.userName().sendKeys(userName);
		login.password().sendKeys(password);
		login.loginButton().click();

		ProductPage productPage = new ProductPage(driver);
		

		
		try {
			if(productPage.title().isDisplayed()){
				actualResult = "Success";
				logger.info("Login Successful..");
			}
		} catch (Exception e) {
			
			
				actualResult = "Failuar";
				logger.info("Login Not Successful..");
			
		}

		Assert.assertEquals(actualResult, expectedResult);

	}

	@DataProvider
	public Object[][] Data() {
		Object[][] data = new Object[2][3];
		data[0][0] = "standard_user";
		data[0][1] = "secret_sauce";
		data[0][2] = "Success";
		data[1][0] = "standard_user";
		data[1][1] = "secret";
		data[1][2] = "Failuar";
		return data;
	}

	@AfterMethod
	public void clouser() {
		 driver.quit();
	}

}
