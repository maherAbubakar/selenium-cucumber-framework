package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement linkMyAccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement linkRegister;
	@FindBy(linkText = "Login")   WebElement linkLogin;
	@FindBy(xpath="//input[@placeholder='Search']")  WebElement searchBox;
	@FindBy(xpath="//div[@id='search']//button[@type='button']") WebElement searchButton;

	public HomePage(WebDriver driver)
	{
		super(driver);
	}

	public void clickMyAccount() {
		try {
			waitTillWebElementIsVisible("linkMyAccount",linkMyAccount);
			waitAndClickOnElement(linkMyAccount);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void clickRegister() {
		try {
			waitTillWebElementIsVisible("linkRegister",linkRegister);
			waitAndClickOnElement(linkRegister);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void clickLogin() {
		try {
			waitTillWebElementIsVisible("linkLogin",linkLogin);
			waitAndClickOnElement(linkLogin);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void enterProductName(String productName)
	{
		sendingValueToWebElement("searchBox",searchBox,productName);
	}

	public void clickSearch()
	{
		try {
			waitAndClickOnElement(searchButton);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
