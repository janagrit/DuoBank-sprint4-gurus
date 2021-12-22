@dbtest
Feature: Sign up feature involving DB layer

  Background: common steps
    Given on login page
    Then I click on sign up page
    Given I am connected to DB


  Scenario: New User Sign Up from UI to DB flow
    When I sign up with the following info
      | first   | last  | email                | password   |
      | Anthony | Fauci | AnthonyFauci@net.com | Fauci12    |

    Then The msg: "Registration Successfull" should appear and user is redirected on Login page
    And The database should also have the correct info with a new user


#  Scenario: New User Creation from DB to UI flow
#    When I add a new user to the database with the following info
#      | first | last    | email             | password  |
#      | Jesse | Watters | Watters@gmail.com | Watters99 |
#    Then I should be able to log in with the email "Watters@gmail.com" and password "Watters99" on the UI

  Scenario: New User Creation from DB to UI flow
    When I add a new user to the database with the following info
      | first | last    | email             | password  | phone      | image | type | created_at| modified_at| zone_id | church_id| country_id| active|
      | Jesse | Watters | Watters@gmail.com | Watters99 | 2129000908 |       | 2    |           |            |    0    |     0    |     0     |  1    |
    Then I should be able to log in with the email "Watters@gmail.com" and password "Watters99" on the UI



  Scenario: Verify Songs Table Column Names
    When I retrieve the column names for the Songs users
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
    Then The msg: "Registration Successfull" should appear and user is redirected on Login page
    And The database should also have the correct info without spaces


    #bug found has duplicates
  Scenario: Check for duplicate values in the username column
    When I send a query to check for duplicate usernames
    Then The returned result list should be empty


  @db_sign
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





















