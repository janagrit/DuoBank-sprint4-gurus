Feature: Verify login features

Background:
  Given on login page

  @1
  Scenario: Logging with the Gurus team credentials
    When I am on login page I filling out email and password and click login button

  @2
  Scenario: Logging with invalid email input
    When I login with invalid email "email@yahoo.com" and password "password" and click login button
    Then I should not be able to login and an error message should be displayed

  @3
  Scenario: Logging with invalid password input
    When I login with the correct email "duobank@yahoo.com" and invalid password "hgytur" and click login button
    Then I should not be able to login and an error message should be displayed

#  bug -> no proper error msg is displayed
  @4
  Scenario: Login without entering data
    When I login with no email "email" and no password "password" and click login button
    Then I should not be able to login and an error message should be displayed


  @excel
  Scenario: Login with data from the Excel file
    When I am on login page using data from the Excel file "SignUp_Data.xlsx"



  Scenario Outline: Verify login feature with Outline
    When Login a user with "<Email>", "<Password>"
#      |  Email   | Password   |
#      | <Email>  | <Password> |
    Examples:
      | Email                     | Password  |
      | fwidmeore0@shareasale.com | TwBqcPP    |
      | ahacecleton1@va.gov       | w1POixUgM |




