package stepDefinitions;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjects.AccountRegistrationPage;
import pageObjects.BasePage;
import pageObjects.HomePage;

import static pageObjects.BasePage.getFormattedDateTime;
import static utilities.DriverFactory.getDriver;

@Slf4j
public class RegistrationSteps {

     HomePage homePage;
     AccountRegistrationPage accountRegistrationPage;
	private final WebDriver driver = getDriver();


	@Given("user navigates to Register Account page")
	public void user_navigates_to_register_account_page() {
		homePage=new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickRegister();
                   
	}

	@When("user enters the details into below fields")
	public void user_enters_the_details_into_below_fields(DataTable dataTable) {
		Map<String, String> dataMap = dataTable.asMap(String.class,String.class);
		accountRegistrationPage=new AccountRegistrationPage(BasePage.getDriver());
		accountRegistrationPage.setFirstName(dataMap.get("firstName"));
		accountRegistrationPage.setLastName(dataMap.get("lastName"));
		accountRegistrationPage.setEmail(getFormattedDateTime()+"@gmail.com");
		accountRegistrationPage.setTelephone(dataMap.get("telephone"));
		accountRegistrationPage.setPassword(dataMap.get("password"));
		accountRegistrationPage.setConfirmPassword(dataMap.get("password"));
	}

	@When("user selects Privacy Policy")
	public void user_selects_privacy_policy() {
		accountRegistrationPage.setPrivacyPolicy();
	}

	@When("user clicks on Continue button")
	public void user_clicks_on_continue_button() {
		accountRegistrationPage.clickContinue();
	}

	@Then("user account should get created successfully")
	public void user_account_should_get_created_successfully() {
		String confirmationMsg=accountRegistrationPage.getConfirmationMsg();
		Assert.assertEquals(confirmationMsg, "Your Account Has Been Created!");
		}
 }
