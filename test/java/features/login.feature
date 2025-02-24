Feature: Login functionality testing with Selenide

  Background:
    Given Set up driver Selenide
    And I open the login page

  Scenario: Successful login with valid credentials
    When I enter email "example@gmail.com"
    And I enter password "12345678"
    And I click the login button
    Then I should see the successful login message

  Scenario: Unsuccessful login with incorrect password
    When I enter email "example@gmail.com"
    And I enter password "AbraCadabra"
    And I click the login button
    Then I should see an error message

  Scenario: Redirect to registration page
    When I click the registration button
    Then I should be redirected to the registration page