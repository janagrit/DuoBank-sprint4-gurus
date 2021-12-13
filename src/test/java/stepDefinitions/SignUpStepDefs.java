Feature:  As a user I sign Up

  Background: common steps
    Given on login page
    Then I click on sign up page


    @7
  Scenario: Verify registration using Excel File
    When I register a new user using data from the Excel file "SignUp_Data.xlsx"
    Then I verify msg "Welcon" or "Buy"




  Scenario Outline: Verify sign up feature with Outline
    When Register a new user: "<FIRST NAME>", "<LAST NAME>", "<EMAIL ADDRESS>", "<PASS>" click sign up button
      | FIRST NAME   | LAST NAME   | EMAIL ADDRESS   | PASS   |
      | <FIRST NAME> | <LAST NAME> | <EMAIL ADDRESS> | <PASS> |

    Examples:
      | FIRST NAME | LAST NAME | EMAIL ADDRESS             | PASS      |
      | Olyea      | eKarenina | Anena@gmail.com           | hghgh     |
      | Meagda     | Magedovna | peep@gmail.com            | sdsll     |
      | jaeva      | geurus    | gureus@gmail.com          | debeunk   |
      | Feliseha   | Widmoere  | fwidmeore0@shareasale.com | TwBcPP    |
      | Anneabel   | Haccleton | ahacecleton1@va.gov       | w1POixUgM |
















