package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
	public WebDriver driver;
	
	public Login(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(id="user-name")
	private WebElement userName;
	
	public WebElement userName() {
		return userName;
	}
	
	@FindBy(id="password")
	private WebElement password;
	
	public WebElement password() {
		return password;
	}
	
	@FindBy(id="login-button")
	private WebElement loginButton;
	
	public WebElement loginButton() {
		return loginButton;
	}
	
	
	
}
