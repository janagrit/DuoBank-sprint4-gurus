
Feature: Credit Report Related Features


  Background:
    Given I am on the main page
    Then Click on Mortgage Application link
    And Verify that I am on Mortgage Application Page
    Then Click on Credit Report Page
    And verify that I am on Credit Report Page


  Scenario: Decline ordering credit report
    When Click NO
  Then Verify if NO is selected
  @tempC
    Scenario:Choice "Yes" to Order credit report should be selected by default
      Then Verify if Yes is selected

    Scenario: Click next to go to the next page
      When I click next , I should be able to go to the next page


