package utilities;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import static java.lang.System.getProperties;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver(){
        if(webDriver.get()==null){
            webDriver.set(createDriver());
        }
        return webDriver.get();
    }

    public static WebDriver createDriver(){
        WebDriver driver = null;
        String browser = getBrowser();

        switch(browser){
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new ChromeDriver(chromeOptions);
                driver.manage().window().maximize();
            }
            case "firefox" ->{
                driver = new FirefoxDriver();
            }
            case "edge" ->{
                driver = new EdgeDriver();
            }
        }
        return driver;
    }

    public static void cleanUp(){
        webDriver.get().quit();
        webDriver.remove();
    }

    public static String getBrowser(){
        FileInputStream file;
        String browser;

        Properties properties = new Properties();
        try {
            file = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        browser = properties.getProperty("browser").toLowerCase().trim();
        return  browser;
    }

    public static String getWebsite(){
        FileInputStream file;
        String website;

        Properties properties = new Properties();
        try {
            file = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        website = properties.getProperty("appURL");
        return  website;
    }

//    static WebDriver driver;
//    static Properties properties;
//
//    public static WebDriver initilizeBrowser() throws IOException {
//        if (getProperties().getProperty("execution_env").equalsIgnoreCase("remote")) {
//            DesiredCapabilities capabilities = new DesiredCapabilities();
//
//            //os
//            if (getProperties().getProperty("os").equalsIgnoreCase("windows")) {
//                capabilities.setPlatform(Platform.WIN11);
//            } else if (getProperties().getProperty("os").equalsIgnoreCase("mac")) {
//                capabilities.setPlatform(Platform.MAC);
//            } else {
//                System.out.println("No matching OS..");
//            }
//            //browser
//            switch (getProperties().getProperty("browser").toLowerCase()) {
//                case "chrome":
//                    capabilities.setBrowserName("chrome");
//                    break;
//                case "edge":
//                    capabilities.setBrowserName("MicrosoftEdge");
//                    break;
//                default:
//                    System.out.println("No matching browser");
//            }
//
//            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
//
//        } else if (getProperties().getProperty("execution_env").equalsIgnoreCase("local")) {
//            switch (getProperties().getProperty("browser").toLowerCase()) {
//                case "chrome":
//                    driver = new ChromeDriver();
//                    break;
//                case "edge":
//                    driver = new EdgeDriver();
//                    break;
//                default:
//                    System.out.println("No matching browser");
//                    driver = null;
//            }
//        }
//        assert driver != null;
//        driver.manage().deleteAllCookies();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
//
//        return driver;
//    }
//
//    public static WebDriver getDriver() {
//        return driver;
//    }
//
//    public static Properties getProperties() throws IOException {
//        FileReader file=new FileReader(".//src//test//resources//config.properties");
//        properties = new Properties();
//        properties.load(file);
//        return properties;
//    }

}
