@dbtest
Feature: Employment and Income Related features involving DB layer


  Background:
    Given I am on the main page
    Then Click on Mortgage Application link
    And Verify that I am on Mortgage Application Page



  Scenario:Fill out Mortgage Application  from UI to DB flow
    Given I am connected to DB
    When I fill out Mortgage Application page using  following info
      | FIRST NAME | LAST NAME | EMAIL              | SSN         | MaterialStatus | CELL PHONE   | MONTHLY RENTAL PAYMENT | EMPLOYER NAME | POSITION | CITY    | START DATE | GROSS MONTHLY INCOME |
      | Peppa      | Pig       | peppapig@gmail.com | 987-65-0987 | Married        | 988-76-7543 | 100                    | Disco       | hero     | Orlando | 1999-11-11   | 10000                |
  Then I am able move to next page
  And The database should  have the correct info


  Scenario: Check for duplicate values in the SSN column
    Given I am connected to the DB
    When I send a query to check for duplicate SSN
    Then The returned result list should be empty.


  Scenario: Check for duplicate values in the email column
    Given I am connected to the DB
    When I send a query to check for email duplicate
    Then The returned result list should be empty.









