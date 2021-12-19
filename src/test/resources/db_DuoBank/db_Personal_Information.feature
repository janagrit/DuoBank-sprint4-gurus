Feature: Complete Personal Information page involving DB layer


  Background:
    Given I am on the homepagee
    When I enter the username and password and than click on login buttonn
    Then I am on the main page clicking on Mortgage Applicationn
    Then After I complete the Preapproval Details page I should land on Personal Information Pagee


  Scenario: Verify that even though customer provide incorrect information, she/he is still able to move to the next page
    Given I am on the personal information page and I am connected to the DB
    When I put the following info
      | FirstName | LastName | Email        | SSN         | MaterialStatus | CellPhone   |
      | Bob       | Cruz     | MS@gmail.com | 123-45-6789 | Married        | 000-00-0000 |
    Then I am able move to the next page
    And The database should also have the correct info





