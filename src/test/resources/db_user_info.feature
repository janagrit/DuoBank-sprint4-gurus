@db_Sprint_6
Feature: User info feature involving DB layer

  Background: common steps
    Given I am connected to DB


#  bug found - there is no option to change the user's info
  Scenario Outline: Verify First & Last name update on the UI through Outline
    Given on login page
    When The user enters the valid credentials as "<email>" for username and "<password>" for password
    Then The user should be able to login and land on the homepage
    Then I can change my info on UI
    Examples: valid username and password list
      | email                     | password     |
      | duobank@gmail.com         | duobank      |
      | tschapero2@woothemes.com  | tEBQRWRCDHRN |
      | spearman5@t-online.de     | lA3NBBya2    |




  Scenario Outline: Verify First & Last name update on the DB
    When I update First "<first>" & Last "<last>" name of user with email "<email>"
    Then on login page
    When The user enters the valid credentials as "<email>" for username and "<password>" for password
    Then The user should be able to login and land on the homepage
    Then I should see the updated First & Last name on the UI
    Examples: valid username and password list
      | email                    | password     | first | last       |
      | duobank@gmail.com        | duobank      | User  | Usercevich |
      | tschapero2@woothemes.com | tEBQRWRCDHRN | Tyler | Schapero   |
      | spearman5@t-online.de    | lA3NBBya2    | Kim   | Potter     |


















