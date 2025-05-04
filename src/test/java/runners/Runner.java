package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
//        features ={"@target/rerun.txt"},
        features = {"classpath:featureFiles"},
        glue = {"stepDefinitions","Hooks"},
        monochrome = true,
        dryRun = false,      // if true, then it will not write the code in Step Definition - only run the feature lines
        tags = "@regression",
        plugin = {"pretty",
                "html:target/CucumberReports/report.html",
                "json:target/CucumberReports/report.json",
                "junit:target/CucumberReports/report.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class Runner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
