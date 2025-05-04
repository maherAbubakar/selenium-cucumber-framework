Feature: Login Data Driven with Excel

  Scenario Outline: Login Data Driven Excel
    Given user opens the application
    And user navigates to login page
    Then user should be redirected to the MyAccount Page by passing email and password with excel row "<row_index>"

    Examples: 
      | row_index |
      |         1 |
      |         2 |
      |         3 |
      |         4 |
      |         5 |
