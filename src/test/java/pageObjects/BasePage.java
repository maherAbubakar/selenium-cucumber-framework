package pageObjects;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

@Slf4j
public class BasePage extends DriverFactory {

	WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor jsExecutor;
	protected Actions builder;

	public BasePage(WebDriver driver) {
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.jsExecutor = ((JavascriptExecutor) driver);
		this.builder = new Actions(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitTillWebElementIsVisible(String webElementName, WebElement webElement) {
		try {
			this.wait.until(ExpectedConditions.visibilityOf(webElement));
			log.info("Element is visible: {} - {}", webElementName, getSelectorOfElement(webElement));
		} catch (Exception exception) {
			log.error("Element is NOT visible: {} - {}. Exception: {}",
					webElementName, getSelectorOfElement(webElement), exception.toString());
		}
	}

	public void waitTillWebElementIsEnabled(String webElementName, String cssSelector) {
		try {
			WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
			boolean isEnabled = element.isEnabled();
			log.info("Element is enabled: {} - {} (enabled: {})", webElementName, cssSelector, isEnabled);
		} catch (Exception exception) {
			log.error("Element is NOT enabled: {} - {}. Exception: {}",
					webElementName, cssSelector, exception.toString());
		}
	}

	public void sendingValueToWebElement(String webElementName, WebElement webElement, String textToSend) {
		try {
			waitTillWebElementIsVisible(webElementName, webElement);
			if (!(webElementName.equalsIgnoreCase("contractRateField") || webElementName.equalsIgnoreCase("residualValueDefinition"))) {
				clearField(webElement);
			}
			Thread.sleep(500);
			webElement.sendKeys(textToSend);
			log.info("Value set in element: {} - '{}', Element: {}", webElementName, textToSend, webElement);
		} catch (Exception exception) {
			log.error("Failed to set value in element: {} - '{}'. Element: {}. Exception: {}",
					webElementName, textToSend, webElement, exception.toString());
		}
	}

	public void actionMoveAndClick(String webElementName, WebElement webElement) {
		Actions action = new Actions(driver);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(webElement)).isEnabled();
			action.moveToElement(webElement).click().build().perform();
			log.info("Element clicked: {} - {}", webElementName, webElement);
		} catch (StaleElementReferenceException staleElementReferenceException) {
			try {
				boolean isWebElementPresent = wait.until(ExpectedConditions.elementToBeClickable(webElement)).isEnabled();
				if (isWebElementPresent) {
					action.moveToElement(webElement).click().build().perform();
					log.info("Element clicked after stale reference: {} - {}", webElementName, webElement);
				}
			} catch (Exception e) {
				log.error("Failed to click element after stale reference: {} - {}. Exception: {}",
						webElementName, webElement, e.toString());
			}
		} catch (Exception exception) {
			log.error("Failed to click element: {} - {}. Exception: {}",
					webElementName, webElement, exception.toString());
		}
	}

	public void waitAndClickOnElement(WebElement element) throws InterruptedException {
		Wait<WebDriver> tempWait = new WebDriverWait(driver, Duration.ofSeconds(2));
		boolean clicked = false;
		int attempts = 0;
		Exception exceptionError = null;
		while (!clicked && attempts < 10) {
			try {
				tempWait.until(ExpectedConditions.elementToBeClickable(element));
				element.click();
				log.info("Element has been clicked: {}", element);
				clicked = true;
			} catch (Exception exception) {
				exceptionError = exception;
				log.warn("Attempt {}: Unable to click element: {}. Exception: {}", attempts + 1, element, exception.getMessage());
				attempts++;
			}
		}
		if (attempts >= 9) {
			log.error("Unable to click element after multiple attempts: {}", getSelectorOfElement(element));
		}
	}

	public static String getSelectorOfElement(WebElement element) {
		String str = element.toString();
		return str.substring(str.indexOf("selector:") + 9, str.length() - 1);
	}

	public void clearField(WebElement element) {
		int lenText = element.getAttribute("value").length();
		for (int i = 0; i < lenText; i++) {
			element.sendKeys(Keys.BACK_SPACE);
		}
	}

	public boolean isWebElementDisplayed(String webElementName, WebElement webElement) {
		boolean isDisplayed = webElement.isDisplayed();
		log.info("Element displayed check: {} - {} (displayed: {})", webElementName, webElement, isDisplayed);
		return isDisplayed;
	}

	public String beautifyWebElement(WebElement webElement) {
		if (webElement.toString().contains("By.cssSelector")) {
			return webElement.toString().substring(webElement.toString().indexOf("cssSelector"), webElement.toString().length() - 1);
		} else {
			return "cssSelector" + webElement.toString().substring(webElement.toString().indexOf("css selector") + 12, webElement.toString().length() - 1);
		}
	}

	public String convertWebElementIntoString(WebElement webElement) {
		return webElement.toString().substring(webElement.toString().indexOf("css selector:") + 14, webElement.toString().length() - 1);
	}

	public static String getFormattedDateTime() {
		return LocalDateTime.now()
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
	}
}