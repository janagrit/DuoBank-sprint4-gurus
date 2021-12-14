@t
Feature: Current Monthly Housing Expenses

  Background: common steps
    Given I am on the homepage
    When I enter the username and password and than click on login button
    Then I am on the main page clicking on Mortgage Application
    Then After I complete the Preapproval Details page I should land on Personal Information Page
    Then I am on Personal Information Page fill in the basic information in order to move forward



  Scenario: Verify validation of Rent Check Box
    When I selecting the Rent checkbox
    Then I enter random digits in the Monthly Rental Payment field
    Then I go to next page


  Scenario: Verify validation of Rent Check Box
    When I selecting the Rent checkbox
    Then I enter huge amount of digits in the Monthly Rental Payment field
    Then I go to next page

  Scenario: Verify validation of Own Check Box
    When I selecting the Own checkbox
    Then I enter random digits in the First Mortagage Payment field
    Then I go to next page

  Scenario: Verify validation of First Mortagage Payment field
    When I selecting the Own checkbox
    Then I enter random, trillion value, digits in the First Mortgage Payment field
    Then I go to next page



