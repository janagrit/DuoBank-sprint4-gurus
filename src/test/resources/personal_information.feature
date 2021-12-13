
Feature: Personal Details Page verification

  Background:
    Given I am on the homepage
    When I enter the username and password and than click on login button
    Then I am on the main page clicking on Mortgage Application
    Then After I complete the Preapproval Details page I should land on Personal Information Page



#  Scenario: Verify that even though a customer don't enter their Bday (required filed), she/he is still able to move to the next page
#   Given The customers is able to move to the next page without providing Bday information

  Scenario Outline: Verify that even though customer provide incorrect information, she/he is still able to move to the next page
    When I click and put first name "<FirstName>",last name "<LastName>",email "<Email>","<SSN>","<MaterialStatus>","<CellPhone>"
    Then I still should be able move to the next page

    Examples:

      | FirstName | LastName | Email        | SSN | MaterialStatus | CellPhone   |
      | 7         | 8        | MS@gmail.com | 0   | Married        | 000-00-0000 |
      | K         | M        | BC@gmail.com | 00  | Separated      | 100-00-0000 |
      | 77@       | Bob      | BC@gmail.com | 9   | Divorced       | 000-00-0001 |


  Scenario: When I am applying with the co-borrower and check YES box, the Co-Borrower's Information should be displayed
    When I check "Yes" box
    Then The Co-Borrower's Information should be displayed

#  Scenario: Verify Application Wizard option list
#
#    Then The Application Wizard option category should include
#
#      | PREAPPROVAL DETAILS                            |
#      | PERSONAL INFORMATION                           |
#      | EXPENSES                                       |
#      | EMPLOYMENT AND INCOME                          |
#      | CREDIT REPORT SETUP YOUR ACCOUNT DETAILS HERE. |
#      | ECONSENT                                       |
#      | SUMMARY                                        |

  Scenario Outline: Verify that even though borrower and co-borrower provide incorrect information,they are still able to move to the next page
    When I check "Yes" box
    Then I click and put first name "<bFirstName>",last name "<bLastName>",email "<bEmail>","<bSSN>","<bMaterialStatus>","<bCellPhone>"
    Then I put the info for co-borrower "<bFirstName>", "<bLastName>", "<bEmail>","<bSSN>","<bMaterialStatus>","<bCellPhone>"
    Then I still should be able move to the next page

    Examples:
      | bFirstName | bLastName | bEmail         | bSSN | bMaterialStatus | bCellPhone  |
      | !bb        | 8         | 567@gmail.com  | 0    | Married         | 000-00-0000 |
      | K          | Lolo      | Lolo@gmail.com | 00   | Separated       | 100-00-0000 |
      | 77@        | Bob       | BC@gmail.com   | 9    | Divorced        | 000-00-0001 |


  Scenario: Verify the customer cannot skip the personal information page

    When I do not enter only some information
      | firstName | lastName | email | SSN |
      | 0         | 0        | 0     | 0   |
    Then I should receive an error message