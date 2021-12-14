
Feature: Verify login features

Background:
  Given on login page


  Scenario: Logging with the Gurus team credentials
    When I am on login page I filling out email and password and click login button
    Then The user should be able to login and land on the homepage


  Scenario: Logging with invalid email input
    When I login with invalid email "email@yahoo.com" and password "password" and click login button
    Then I should not be able to login and an error message should be displayed


  Scenario: Logging with invalid password input
    When I login with the correct email "duobank@yahoo.com" and invalid password "hgytur" and click login button
    Then I should not be able to login and an error message should be displayed

#  bug -> no proper error msg is displayed

  Scenario: Login without entering data
    When I login with no email "email" and no password "password" and click login button
    Then I should not be able to login and an error message should be displayed


  Scenario: Login with data from the Excel file
    When I am on login page using data from the Excel file "SignUp_Data.xlsx"


  Scenario Outline: Login using valid credentials through Outline
    When The user enters the valid credentials as "<email>" for username and "<password>" for password
    Then The user should be able to login and land on the homepage
    Examples: valid username and password list
      | email                     | password     |
      | duobank@gmail.com         | duobank      |
      | tschapero2@woothemes.com  | tEBQRWRCDHRN |
      | spearman5@t-online.de     | lA3NBBya2    |


#    bug - a proper error msg is not displaed
  Scenario Outline: Verify login feature with invalid credentials using Outline
    When Login a user with "<Email>", "<Password>"
    Then I should not be able to login and an error message should be displayed
    Examples: valid username and password list
      | Email                       | Password   |
      | fwidmeore0@shareasale.com   | TwBqcPP    |
      | ahachhecleton1@va.gov       | wi1POixUgM |
      | ahaceton1@vda.gov           | OixUgM6tew |






