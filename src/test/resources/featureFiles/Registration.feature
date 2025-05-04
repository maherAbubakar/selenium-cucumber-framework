Feature: Account Registration

  @regression
  Scenario: Successful Account Registration
    Given user opens the application
    And user navigates to Register Account page
    When user enters the details into below fields
      | firstName | John       |
      | lastName  | Kenedy     |
      | telephone | 1234567890 |
      | password  | test@123   |
    And user selects Privacy Policy
    And user clicks on Continue button
    Then user account should get created successfully
