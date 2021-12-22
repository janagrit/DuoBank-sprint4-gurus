Feature: Sign up feature involving DB layer

  Background: common steps
    Given on login page
    Then I click on sign up page
    Given I am connected to DB

  @db_sign
  Scenario: New User Sign Up from UI to DB flow
    When I sign up with the following info
      | first | last  | email              | password   |
      | Bob   | Evans | bobevans@gmail.com | bobevans23 |
#    Then I should land on the login page
    Then The msg: "Registration Successfull" should appear and user is redirected on Login page
    And The database should also have the correct info with a new user
























