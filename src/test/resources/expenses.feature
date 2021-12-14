Feature: Current Monthly Housing Expenses

  Background: common steps
    Given on login page
    When I am on login page I filling out email and password and click login button
    Then Click on Mortgage Application link
    And Verify that I am on Mortgage Application Page
    Then I am on on the preapproval page filling a name "Vasia", purchase price 10000, down payment 1000 and click next button
#    Then I am on the personal information page put name "Alice", surname "Abe", email "email", social 1111111111, phone 111111111


  Scenario: Verify Expenses page
    Then user put 2000 as a monthly payment and click next button



#
#
#  Scenario: Verify validation of Rent Check Box
#    When I selecting the Rent checkbox
#    Then I enter random digits in the Monthly Rental Payment field
#    Then I go to next page
#
#  @smoke @regression
#  Scenario: Verify validation of Own Check Box
#    When I selecting the Own checkbox
#    Then I enter random digits in the First Mortagage Payment field
#    Then I go to next page
#
#  @smoke @regression #bug
#  Scenario: Verify validation of First Mortagage Payment field
#    When I selecting the Own checkbox
#    Then I enter random, trillion value, digits in the First Mortgage Payment field
#    Then I go to next page
#
#  @smoke @regression #bug
#  Scenario: Verify validation of Rental Payment field
#    When I selecting the Rent checkbox
#    Then I enter random, trillion value, digits in the Monthly Rental Payment field
#    Then I go to next page
#
#
