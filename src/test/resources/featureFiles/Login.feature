Feature: Login with Valid Credentials

  @sanity @regression
  Scenario: Successful Login with Valid Credentials
    Given user opens the application
    And user navigates to login page
    When user enters email as "qa.engineer@gmail.com" and password as "qa@123"
    And user clicks on the Login button
    Then user should be redirected to the MyAccount Page

  @regression
  Scenario Outline: Login Data Driven
    Given user opens the application
    And user navigates to login page
    When user enters email as "<email>" and password as "<password>"
    And user clicks on the Login button
    Then user should be redirected to the MyAccount Page

    Examples:
      | email                     | password |
      | qa.engineer@gmail.com     | qa@123   |
      | pavanoltraining@gmail.com | test@123 |
