@employment
Feature: Employment and Income Related features involving DB layer


  Background:
    Given I am on the main page
    Then Click on Mortgage Application link
    And Verify that I am on Mortgage Application Page


@db
  Scenario:Fill out Mortgage Application  from UI to DB flow
    Given I am connected to DB
    When I fill out Mortgage Application page using  following info
      | REALTOR INFO | ESTIMATED PURCHASE PRICE | DOWN PAYMENT AMOUNT | DOWN PAYMENT PERCENTAGE | FIRST NAME | LAST NAME | EMAIL              | SSN         | MaterialStatus | CELL PHONE   | MONTHLY RENTAL PAYMENT | EMPLOYER NAME | POSITION | CITY    | STATE | START DATE | GROSS MONTHLY INCOME |
      | abcMouse     | 50000                    | 500                 | 50                      | Peppa      | Pig       | peppapig@gmail.com | 987-65-0987 | Married        | 988-767-5432 | 100                    | Disney        | Pig      | Orlando | FL    | 08/16/1888 | 10000                |
  Then I am able move to next page
  And The database should  have the correct info


