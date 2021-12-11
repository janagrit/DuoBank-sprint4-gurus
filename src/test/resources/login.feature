Feature: Verify login features

Background:
  Given on login page

  @login
  Scenario: Logging to main page
    Given on login page
    When I am on login page I filling out email and password and click login button


#  Scenario : Verify login feature
#    Given on login page
#    Then filling the email "EMAIL ADDRESS" and password "PASSWORD" and click Login button

  Scenario: Verify credentials with the excel file Sheet1
    When I am on login page using the given excel file


#  Scenario: Verify the negative Login test
#    When on login page
#    Then The user should fails by putting the wrong credentials "username", "password"
#
#
#
#  Scenario: Verify the positive Login test
#    When The user navigates and clicking on menu button -> to be trasnfered to SING IN page
#    Then The user should login successfully into his account and see the Main page with Upcoming orders
#
#

