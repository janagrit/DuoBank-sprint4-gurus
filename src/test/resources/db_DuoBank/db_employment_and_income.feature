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
      | FIRST NAME | LAST NAME | EMAIL              | SSN         | MaterialStatus | CELL PHONE   | MONTHLY RENTAL PAYMENT | EMPLOYER NAME | POSITION | CITY    | START DATE | GROSS MONTHLY INCOME |
      | Peppa      | Pig       | peppapig@gmail.com | 987-65-0987 | Married        | 988-76-75432 | 100                    | Disney        | Pig      | Orlando | 1888-08-16 | 10000                |
  Then I am able move to next page
  And The database should  have the correct info


