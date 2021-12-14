Feature:  As a user I sign Up

  Background: common steps
    Given on login page
    Then I click on sign up page

  @8
  Scenario: Verify registration by using the Excel File
    When I register a new user using data from the Excel file "SignUp_Data.xlsx"
    Then The msg: "This email already used" should appear on the sign up page


  Scenario: Verify registration with the Faker class
    When I register a new user with Faker class
    Then The msg: "Registration Successfull" should appear and user is redirected on Login page

  @7
  Scenario Outline: Verify sign up feature with Outline
    When Register a new user: "<FIRST NAME>", "<LAST NAME>", "<EMAIL>", "<PASS>" click sign up button
    Examples: valid username and password list
      | FIRST NAME | LAST NAME | EMAIL                        | PASS      |
      | Coco       | Chanel    | coco@gmail.com               | cococo    |
      | Hinda      | Ervine    | hervine9@liveinternet.ru     | SCNCof    |
      | Neille     | Scarre    | nscarrea@nymag.com           | aixi6RY   |
      | Sula       | Ledwidge  | sledwidgeb@timesonline.co.uk | dcXjoMugZ |
















