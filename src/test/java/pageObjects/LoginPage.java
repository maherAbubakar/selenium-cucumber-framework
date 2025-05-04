package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	@FindBy(xpath = "//input[@id='input-email']") WebElement emailAddressInput;

	@FindBy(xpath = "//input[@id='input-password']") WebElement passwordInput;

	@FindBy(xpath = "//input[@value='Login']") WebElement loginButton;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void setEmail(String email) {
		waitTillWebElementIsVisible("email",emailAddressInput);
		sendingValueToWebElement("email",emailAddressInput,email);
	}

	public void setPassword(String password) {
		waitTillWebElementIsVisible("password",passwordInput);
		sendingValueToWebElement("password",passwordInput,password);
	}

	public void clickLogin() {
		try {
			waitTillWebElementIsVisible("loginButton",loginButton);
			waitAndClickOnElement(loginButton);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
