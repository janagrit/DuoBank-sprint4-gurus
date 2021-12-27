@db_Sprint_6
Feature: E_consent related features

  Background:
    Given I am on the homepage.
    When I enter the username and password and than click on login button.
    Then I am on the main page clicking on Mortgage Application.
    Then After I complete all required information I should land on Econcent page
    Then verify that I am on Econcent page


   Scenario Outline: Sign Econsent using scenario outline
     Given I am connected to DB
    When I fill up the fields with the following user information
      | First Name  | Last Name  | Email   |
      | <firstName> | <lastName> | <email> |
    Then This information should be stored properly in the database

    Examples:
      | firstName | lastName | email           |
      | Santa     | Claus    | santa@gmail.com |
      | Peppa     |Pig       |peppa@gmail.com  |



   Scenario: Verify information from the database
     Given I am connected to DB
  When I send the query to update econsent page first name, last name and email
  Then The actual output from the query should match the expected one that I sent to query



  Scenario: Verify mortgage table eConcent columns
    Given I am connected to DB
    When I send a query to database to retrieve the names of columns on the eConcent page table
    Then The retrieved columns should be similar to the expected columns



  Scenario: Verify that the database supports unicode entries
    Given I am connected to DB
    When I send a query to update realtor info field with unicode value
    Then The database should update the entry


  Scenario: Verify that the database throws exception if the primary key column is updated with null value
    When I send query to update the primary key column with null value
    Then The database should throw an exception









