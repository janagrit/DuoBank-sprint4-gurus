Feature:  As a user I sign Up

  Background: common steps
    Given on login page
    Then I click on sign up page


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







  Scenario Outline: Verify sign up russian and chinese names but english email
    When Register a new user: "<FIRST NAME>", "<LAST NAME>", "<EMAIL ADDRESS>", "<PASSWORD>" click sign up button
      | FIRST NAME   | LAST NAME   | EMAIL ADDRESS   | PASSWORD |
      | <FIRST NAME> | <LAST NAME> | <EMAIL ADDRESS> | <PASSWORD>|
    Examples:
      | FIRST NAME | LAST NAME | EMAIL ADDRESS            | PASSWORD|
      | Таня       | Алегрова  | ttania@gmail.com          | 1234599|
      | 雅哪        | 女        | gturus@gmail.com          | i8i8i    |









