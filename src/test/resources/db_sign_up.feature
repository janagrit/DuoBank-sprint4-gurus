
Feature: Sign up feature involving DB layer

  Background: common steps
    Given on login page
    Then I click on sign up page
    Given I am connected to DB


  Scenario: New User Sign Up from UI to DB flow
    When I sign up with the following info
      | first   | last  | email                | password   |
      | Santa   | Fauci | SantaFauci@net.com   | Santa12    |

    Then The user should be successfully registered and transfer to Login page
    And The database should also have the correct info with a new user



  Scenario: New User Creation from DB to UI flow
    When I add a new user to the database with the following info
      | email             | password  | first | last    | phone      | image | type | created_at| modified_at| zone_id | church_id| country_id| active|
      | Watters@gmail.com | Watters99 | Jesse | Watters | 2129000908 |       | 2    |           |            |    0    |     0    |     0     |  1    |
    Then I should be able to log in with the email "Watters@gmail.com" and password "Watters99" on the UI



  Scenario: Verify users Column Names - Mapping
    When I retrieve the column names of the required fields
    Then It should be the following
      | id          |
      | email       |
      | password    |
      | first_name  |
      | last_name   |
      | phone       |
      | image       |
      | type        |
      | created_at  |
      | modified_at |
      | zone_id     |
      | church_id   |
      | country_id  |
      | active      |



    #bug found
  Scenario: Test if input field leading and trailing spaces are truncated before committing data to the database
    When I sign up with the following info  "    Anthony   "  "   Fauci  "  "    Fauci@gmail.com     "  "Fauci66"
    Then The user should be successfully registered and transfer to Login page
    And The database should also have the correct info without spaces




    #bug found has duplicates

  Scenario: Check for duplicate values in the email column
    When I send a query to check for duplicate emails and verify
    Then The returned result list should be empty or not



  Scenario Outline: Sign up a new user
    When I fill up the fields with the following new user information
      | First Name  | Last Name  | Email   | Password |
      | <firstName> | <lastName> | <email> |<password> |
    Then This information should be stored correctly in the database

    Examples:
      | firstName | lastName | email                        | password  |
      | Coco      | Chanel   | coco@gmail.com               | cococo    |
      | Hinda     | Ervine   | hervine9@liveinternet.ru     | SCNCof    |
      | Neille    | Scarre   | nscarrea@nymag.com           | aixi6RY   |
      | Sula      | Ledwidge | sledwidgeb@timesonline.co.uk | dcXjoMugZ |



     #bug found - DB is registering a new user with Chinese email address, but login page doesn’t accept this type of email
  Scenario: Verify support of Chinese Char from DB to UI flow
    When I add a new user to the database with the following info
      | email       | password | first | last | phone      | image | type | created_at | modified_at | zone_id | church_id | country_id | active |
      | 眼啊@亚麻.com | 眼啊112    | 雅哪    | 王    | 2129000908 |       | 2    |            |             | 0       | 0         | 0          | 1      |
    Then I should be able to log in with the email "眼啊@亚麻.com" and password "眼啊112" on the UI





















