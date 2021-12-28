@dbtest
Feature: Complete Personal Information page involving DB layer


  Background:
    Given I am on the homepage.
    When I enter the username and password and than click on login button.
    Then I am on the main page clicking on Mortgage Application.
    Then After I complete the Preapproval Details page I should land on Personal Information Page.


  Scenario: Verify that even though customer provide incorrect information, she/he is still able to move to the next page
    Given I am on the personal information page and I am connected to the DB
    When I put the following info
      | FirstName | LastName | Email        | SSN         | MaterialStatus | CellPhone   |
      | Bob       | Cruz     | MS@gmail.com | 123-45-6789 | Married        | 000-00-0000 |
    Then I am able move to the next page
    And The database should also have the correct info


  Scenario: Verify that even though borrower and co-borrower provide incorrect information,they are still able to move to the next page
    Given I am on the personal information page and I am connected to the DB
    Then I check "Yes" box
    Then The Co-Borrower's Information should be displayed.
    When I put the following info for borrower
      | FirstName | LastName   | Email              | SSN         | MaterialStatus | CellPhone    |
      | Marcin    | Kukurykuku | Kukuryku@gmail.com | 123-45-6789 | Married        | 703-67-09090 |
    Then I put the following info for co-borrower
      | FirstName1 | LastName1 | Email1          | SSN1        | DOB1       | MaterialStatus1 | CellPhone1  |
      | Barabasz   | Mulate     | mulat@gmail.com | 123-45-0000 | 12/01/2000 | Married         | 401-98-9898 |
    Then I still should be able move to the next page.
    And The database should also contain the correct info.



#  Scenario: Test if input field leading and trailing spaces are truncated before committing data to the database
#    Given I am on the sign up page and I am connected to the DB
#    When I put the following info
#      | FirstName | LastName | Email        | SSN          | MaterialStatus | CellPhone    |
#      | Marta     | Bober    | MB@gmail.com | 890-980-9090 | Married        | 309-908-7890 |
#    Then I am able move to the next page
#    And The database should also have the correct info without spaces








