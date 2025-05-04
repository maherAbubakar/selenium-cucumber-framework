🚀 OpenCart BDD Test Automation Framework

![Selenium](https://img.shields.io/badge/Selenium-4.25.0-43B02A?logo=selenium)
![Cucumber](https://img.shields.io/badge/Cucumber-7.x-23D96C?logo=cucumber)
![TestNG](https://img.shields.io/badge/TestNG-7.9.0-DD0031)
![Java](https://img.shields.io/badge/Java-17-007396?logo=java)

A Robust BDD Test Automation Framework combining Page Object Model, Data-Driven approaches for OpenCart testing.

📌 Features
📌 Features

✅ BDD with Cucumber (Gherkin syntax)
✅ Cross-browser testing (Chrome, Firefox, Edge)
✅ Parallel execution support
✅ Rich reporting (ExtentReports)
✅ Parallel test execution
✅ Comprehensive reporting (ExtentReports + Log4j2)
✅ CI/CD ready (Jenkins integration)

## 🛠 Technologies
| Component | Version |
|-----------|---------|
| Java      | 17      |
| Cucumber  | 7.22    |
| Selenium  | 4.25.0  |
| TestNG    | 7.9.0   |
| Maven     | 3.9.9   |


### Prerequisites
- Java 17 JDK
- Cucumber 7.22
- Maven 3.8+
- OpenCart installed (WAMP/XAMPP) - https://www.opencart.com/index.php?route=cms/download

### Installation bash
git clone https://github.com/maherAbubakar/selenium-cucumber-framework.git
cd selenium-cucumber-framework

### Configuration
Update config.properties:
* appURL=http://localhost/opencart
* execution_env=local or 'remote' for grid
* browser=chrome

### 🧪 Running Tests
**Test Suites**
| File                   | Purpose                      |
|----------------------- |------------------------------|
| ruuner class           | For Running specific tags    |
| fmainRunner.xml        | For running end-to-end test  |


### 📊 Reports
ExtentReports: SparkReport/Test-Report.html

TestNG Reports: target/surefire-reports

Screenshots: screenshots/ (on failure)

### 🤝 Contribution
Contributions are welcome! Please read contribution.md before submitting a pull request.

### 📧 Author
Abubakar

### 📧 Contact
Email: maherabubakar80@gmail.com

LinkedIn: https://www.linkedin.com/in/abubakar-saleem/
