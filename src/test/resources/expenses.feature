Feature: Current Monthly Housing Expenses

  Background: common steps
    Given on login page
    When I am on login page I filling out email and password and click login button
    Then Click on Mortgage Application link
    And Verify that I am on Mortgage Application Page
    Then I am on on the preapproval page filling a name "Vasia", purchase price 10000, down payment 1000 and click next button
    Then I am on the personal information page put name "Alice", surname "Abe", email "email", social 1111111111, phone 111111111

  @7
  Scenario: Verify Expenses page
    Then user put 2000 as a monthly payment and click next button
