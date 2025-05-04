package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	@FindBy(xpath = "//h2[text()='My Account']") WebElement messageHeading;

	@FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']") WebElement logOut;

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}


	public boolean isMyAccountPageExists()   // MyAccount Page heading display status
	{
		try {
			waitTillWebElementIsVisible("messageHeading",messageHeading);
			return (messageHeading.isDisplayed());
		} catch (Exception e) {
			return (false);
		}
	}

	public void clickLogout() {
		try {
			waitTillWebElementIsVisible("logOut",logOut);
			waitAndClickOnElement(logOut);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
}
