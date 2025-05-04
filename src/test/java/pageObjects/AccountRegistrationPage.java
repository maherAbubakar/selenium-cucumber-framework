package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.BasePage;

public class AccountRegistrationPage extends BasePage {

	@FindBy(xpath="//input[@id='input-firstname']") WebElement firstNameInput;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement lastNameInput;
	@FindBy(xpath="//input[@id='input-email']") WebElement emailInput;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement telephoneInput;
	@FindBy(xpath="//input[@id='input-password']") WebElement passwordInput;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement confirmPasswordInput;
	@FindBy(xpath="//input[@name='agree']") WebElement checkPolicy;
	@FindBy(xpath="//input[@value='Continue']") WebElement continueButton;
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']") WebElement confirmationMessage;


	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}

	public void setFirstName(String firstName) {
		waitTillWebElementIsVisible("firstName",firstNameInput);
		sendingValueToWebElement("firstName",firstNameInput,firstName);
	}

	public void setLastName(String lastName) {
		waitTillWebElementIsVisible("lastName",lastNameInput);
		sendingValueToWebElement("lastName",lastNameInput,lastName);
	}

	public void setEmail(String email) {
		waitTillWebElementIsVisible("email",emailInput);
		sendingValueToWebElement("email",emailInput,email);
	}

	public void setTelephone(String telephone) {
		waitTillWebElementIsVisible("telephone",telephoneInput);
		sendingValueToWebElement("telephone",telephoneInput,telephone);
	}

	public void setPassword(String password) {
		waitTillWebElementIsVisible("password",passwordInput);
		sendingValueToWebElement("password",passwordInput,password);
	}

	public void setConfirmPassword(String confirmPassword) {
		waitTillWebElementIsVisible("confirmPassword",confirmPasswordInput);
		sendingValueToWebElement("confirmPassword",confirmPasswordInput,confirmPassword);
	}

	public void setPrivacyPolicy() {
		try {
			waitTillWebElementIsVisible("checkPolicy",checkPolicy);
			waitAndClickOnElement(checkPolicy);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void clickContinue() {
		//sol1
		try {
			waitTillWebElementIsVisible("continueButton",continueButton);
			waitAndClickOnElement(continueButton);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		//sol2
		//continueButton.submit();

		//sol3
		//Actions act=new Actions(driver);
		//act.moveToElement(continueButton).click().perform();

		//sol4
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();", continueButton);

		//Sol 5
		//continueButton.sendKeys(Keys.RETURN);

		//Sol6
		//WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();

	}

	public String getConfirmationMsg() {
		try {
			waitTillWebElementIsVisible("confirmationMessage",confirmationMessage);
			return (confirmationMessage.getText());
		} catch (Exception e) {
			return (e.getMessage());

		}

	}
	}
