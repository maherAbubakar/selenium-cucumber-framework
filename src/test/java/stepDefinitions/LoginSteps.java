package stepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BasePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataReader;

import static utilities.DriverFactory.getDriver;

@Slf4j
public class LoginSteps {

    HomePage homePage;
    LoginPage loginPage;
    MyAccountPage myAccountPage;

    @Given("user navigates to login page")
    public void user_navigate_to_login_page() {
        log.info("Go to my account --> Click on Login..");
        homePage = new HomePage(getDriver());
        homePage.clickMyAccount();
        homePage.clickLogin();
    }


    @When("user enters email as {string} and password as {string}")
    public void user_enters_email_as_and_password_as(String email, String pwd) {
        log.info("Entering email and password.. ");
        loginPage = new LoginPage(getDriver());
        loginPage.setEmail(email);
        loginPage.setPassword(pwd);
    }

    @When("user clicks on the Login button")
    public void click_on_login_button() {
        loginPage.clickLogin();
        log.info("clicked on login button...");
    }


    @Then("user should be redirected to the MyAccount Page")
    public void user_navigates_to_my_account_page() {
        myAccountPage = new MyAccountPage(getDriver());
        boolean targetPage = myAccountPage.isMyAccountPageExists();
        Assert.assertTrue(targetPage);

    }

    //*******   Data Driven test **************
    @Then("user should be redirected to the MyAccount Page by passing email and password with excel row {string}")
    public void check_user_navigates_to_my_account_page_by_passing_email_and_password_with_excel_data(String rows) {
        List<HashMap<String, String>> dataMap = null;
        try {
            dataMap = DataReader.data(System.getProperty("user.dir") + "/src/test/java/testData/Opencart_LoginData.xlsx", "Sheet1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        int index = Integer.parseInt(rows) - 1;
        String email = dataMap.get(index).get("username");
        String password = dataMap.get(index).get("password");
        String expectedResult = dataMap.get(index).get("result");

        loginPage = new LoginPage(getDriver());
        loginPage.setEmail(email);
        loginPage.setPassword(password);

        loginPage.clickLogin();
        myAccountPage = new MyAccountPage(getDriver());

        boolean targetPage = myAccountPage.isMyAccountPageExists();
        System.out.println("target page: " + targetPage);
        if (expectedResult.equalsIgnoreCase("Valid")) {
            if (targetPage) {
                MyAccountPage myAccountPage = new MyAccountPage(getDriver());
                myAccountPage.clickLogout();
                Assert.assertTrue(true);
            } else {
                Assert.fail();
            }
        }

        if (expectedResult.equalsIgnoreCase("Invalid")) {
            if (targetPage) {
                myAccountPage.clickLogout();
                Assert.fail();
            } else {
                Assert.assertTrue(true);
            }
        }


    }

}
