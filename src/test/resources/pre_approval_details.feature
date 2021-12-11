@smoke
Feature: As a customer I should be able to enter preapproval details.

  Background:
    Given I am on the Main page
    When  I click on the Mortgage application link

@Preapproval
Scenario: Go to the mortgage application page
    Then  I land on the mortgage application page

@Preapproval
  Scenario: Click on the realtor check box
    When  I click on the realtor check box
    Then  Realtor box is checked


 #    Scenario: Enter realtor information
 #    When  I enter realtor information
 #   Then  Realtor info is displayed

#@Preapproval
  Scenario: Click on the loan officer check box
     When  I click on the loan officer check box
     Then  Loan officer box is checked

  # Scenario: Choose the loan purpose
  #   When  I choose the loan purpose
  #   Then  Loan purpose is displayed

@Preapproval
  Scenario: Enter estimated purchase "<price>"
     When  I enter estimated purchase "<price>"
     Then  Purchase "<price>" is displayed

  Examples:
    | price |
    | 500000 |
    | 600000 |
    | 700000 |

#@Preapproval
   Scenario: Enter down payment amount
       When  I enter down payment amount
       Then  Down payment amount is displayed

#@Preapproval
   Scenario: Enter down payment percentage
       When  I enter down payment percentage
       Then  Down payment percentage is displayed

#@Preapproval
   Scenario:  Choose the source of down payment
       When  I choose the source of down payment
       Then  Source of down payment is displayed