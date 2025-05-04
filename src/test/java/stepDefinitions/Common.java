package stepDefinitions;

import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

import static utilities.DriverFactory.getDriver;
import static utilities.DriverFactory.getWebsite;

public class Common {

    private final WebDriver driver = getDriver();
    @Given("user opens the application")
    public void user_opens_the_application(){
           String website = getWebsite();
           driver.get(website);
    }
}
