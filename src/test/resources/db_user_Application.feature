@db_Sprint_6
Feature: Verify Application details


  Scenario: Verify Users Application details
    Given on login page
    When I am on login page I filling out email and password and click login button
    When I land on a Dashboard and I click on Application list
    When I click view Details to open application
#    Then I verify Application details are matching to Application details in the DB