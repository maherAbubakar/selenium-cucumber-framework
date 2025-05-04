package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

    @FindBy(xpath = "//input[@id='input-payment-firstname']") WebElement firstNameInputField;
    @FindBy(xpath = "//input[@id='input-payment-lastname']") WebElement lastNameInputField;
    @FindBy(xpath = "//input[@id='input-payment-address-1']") WebElement firstAddressInputField;
    @FindBy(xpath = "//input[@id='input-payment-address-2']") WebElement secondAddressInputField;
    @FindBy(xpath = "//input[@id='input-payment-city']") WebElement cityInputField;
    @FindBy(xpath = "//input[@id='input-payment-postcode']") WebElement pinInputField;
    @FindBy(xpath = "//select[@id='input-payment-country']") WebElement countryInputField;
    @FindBy(xpath = "//select[@id='input-payment-zone']") WebElement stateInputField;
    @FindBy(xpath = "//input[@id='button-payment-address']") WebElement continueBillingAddress;
    @FindBy(xpath = "//input[@id='button-shipping-address']") WebElement continueDeliveryAddress;
    @FindBy(xpath = "//textarea[@name='comment']") WebElement deliveryMethodInputField;
    @FindBy(xpath = "//input[@id='button-shipping-method']") WebElement continueShippingAddress;
    @FindBy(xpath = "//input[@name='agree']") WebElement selectTermsAndConditionsCheckbox;
    @FindBy(xpath = "//input[@id='button-payment-method']") WebElement continuePaymentMethod;
    @FindBy(xpath = "//strong[text()='Total:']//following::td") WebElement labelTotalPrice;
    @FindBy(xpath = "//input[@id='button-confirm']") WebElement confirmationOrder;
    @FindBy(xpath = "//*[@id='content']/h1") WebElement labelOrderConfirmationMsg;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void setFirstName(String firstName) {
        waitTillWebElementIsVisible("firstName", firstNameInputField);
        sendingValueToWebElement("firstName", firstNameInputField, firstName);
    }

    public void setLastName(String lastName) {
        waitTillWebElementIsVisible("lastName", lastNameInputField);
        sendingValueToWebElement("lastName", lastNameInputField, lastName);
    }


    public void setFirstAddress(String firstAddress) {
        waitTillWebElementIsVisible("firstAddress", firstAddressInputField);
        sendingValueToWebElement("firstAddress", firstAddressInputField, firstAddress);
    }


    public void setSecondAddress(String secondAddress) {
        waitTillWebElementIsVisible("secondAddress", secondAddressInputField);
        sendingValueToWebElement("secondAddress", secondAddressInputField, secondAddress);
    }


    public void setCity(String city) {
        waitTillWebElementIsVisible("city", cityInputField);
        sendingValueToWebElement("city", cityInputField, city);
    }


    public void setPin(String pin) {
        waitTillWebElementIsVisible("pin", pinInputField);
        sendingValueToWebElement("pin", pinInputField, pin);
    }


    public void setCountry(String country) {
        waitTillWebElementIsVisible("country", countryInputField);
        sendingValueToWebElement("country", countryInputField, country);
    }


    public void setState(String State) {
        waitTillWebElementIsVisible("state", stateInputField);
        new Select(stateInputField).selectByVisibleText(State);
    }

    public void clickOnContinueAfterBillingAddress() {
        waitTillWebElementIsVisible("continueBillingAddress", continueBillingAddress);
        try {
            waitAndClickOnElement(continueBillingAddress);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickOnContinueAfterDeliveryAddress() {
        waitTillWebElementIsVisible("continueDeliveryAddress", continueDeliveryAddress);
        try {
            waitAndClickOnElement(continueDeliveryAddress);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void setDeliveryMethodComment(String deliveryMethod) {
        waitTillWebElementIsVisible("deliveryMethod", deliveryMethodInputField);
        sendingValueToWebElement("deliveryMethod", deliveryMethodInputField, deliveryMethod);
    }

    public void clickOnContinueAfterDeliveryMethod() {
        waitTillWebElementIsVisible("continueShippingAddress", continueShippingAddress);
        try {
            waitAndClickOnElement(continueShippingAddress);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectTermsAndConditions() {
        waitTillWebElementIsVisible("checkBoxTerms", selectTermsAndConditionsCheckbox);
        try {
            waitAndClickOnElement(selectTermsAndConditionsCheckbox);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickOnContinueAfterPaymentMethod() {
        waitTillWebElementIsVisible("continuePaymentMethod", continuePaymentMethod);
        try {
            waitAndClickOnElement(continuePaymentMethod);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTotalPriceBeforeConfOrder() {
        waitTillWebElementIsVisible("labelTotalPrice",labelTotalPrice);
        return labelTotalPrice.getText(); //$207.00

    }

    public void clickOnConfirmOrder() {
        waitTillWebElementIsVisible("confirmationOrder", confirmationOrder);
        try {
            waitAndClickOnElement(confirmationOrder);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isOrderPlaced() throws InterruptedException {
        try {
            driver.switchTo().alert().accept();
            waitTillWebElementIsVisible("labelOrderConfirmationMsg", labelOrderConfirmationMsg);
            waitAndClickOnElement(labelOrderConfirmationMsg);
            if (labelOrderConfirmationMsg.getText().equals("Your order has been placed!"))
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }

    }

}
