Feature: As a user I should be able to login
  @run
  Scenario: Logging to main page
    Given on login page
    When I am on login page I filling out email and password and click login button
