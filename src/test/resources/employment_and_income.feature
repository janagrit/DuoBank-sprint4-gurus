@employment
Feature: Employment and Income Related feature

  Background:
    Given I am on the main page
    Then Click on Mortgage Application link
    And Verify that I am on Mortgage Application Page
    Then Click on  Employment and Income Page


    Scenario: Lend on Employment and Income Page
      Given Click on  Employment and Income Page
      And Verify that I am on Employment and Income Page


        Scenario: On "Employer 1" section  "This is my current job" checkBox is selected by default.
        Given  Verify that checkbox is selected

        Scenario:I enter information from Excel File named "Employer1" for required fields with different sets of data
        Given I enter information from Excel File named "Employer1" for required fields

        Scenario Outline:Fill out "Borrower Gross Monthly Employment Income" fields using different sets of data
        When I click and enter gross monthly income"<Gross Monthly Income>",monthly overtime "<Monthly Overtime>",monthly bonuses"<Monthly Bonuses>",monthly commissions"<Monthly Commissions>", monthly dividents"<Monthly Dividents>"

    Examples:
      | Gross Monthly Income   | Monthly Overtime  | Monthly Bonuses   | Monthly Commissions  |Monthly Dividents|
      |8000                    |700                |900                |1000                  |89               |
      |5                       |5                  |5                  |5                     |5                |
      |78TR                    |7^8g               |W234               |Sos                   |                 |

      Scenario Outline: Fill out "Additional Gross Monthly Income" section with different sets of data
      When I fill out "<INCOME SOURCE_1>","<AMOUNT_1>","<INCOME SOURCE_2>","<AMOUNT_2>","<INCOME SOURCE_3>","<AMOUNT_3>"
  Examples:
  |INCOME SOURCE_1                  | AMOUNT_1|  INCOME SOURCE_2     |AMOUNT_2|INCOME SOURCE_3       |AMOUNT_3|
  |Alimnoy\Child Support            |1000     |                      |        |Others Type Of Income |***     |
  |Others Type Of Income            |900      |Alimnoy\Child Support |        |     ***%             |RT      |
  |Social Security\Disability Income|0        |                      |9000    |  7Uyh                |null    |


  Scenario: Verify the customer cannot skip Employment and Income  page

    When I will leave required fields empty and go to next page


    Then message "THIS FIELD IS REQUIRED." should be displayed













