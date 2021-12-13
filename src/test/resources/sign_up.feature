Feature:  As a user I sign Up

  Background: common steps
    Given on login page
    Then I click on sign up page

  @10
  Scenario Outline: Verify sign up feature using proper sets of data
    When on sign up page I fill out the fields: name "<FIRST NAME>", surname "<LAST NAME>" and email "<EMAIL ADDRESS>" and password "<PASSWORD>" click sign up button

      | FIRST NAME   | LAST NAME   | EMAIL ADDRESS   | PASSWORD
      | <FIRST NAME> | <LAST NAME> | <EMAIL ADDRESS> | <PASSWORD>

#   // And The new user data should be stored in database file

    Examples:
      | FIRST NAME | LAST NAME | EMAIL ADDRESS            | PASSWORD
      | Olyea       | eKarenina  | Anena@gmail.com           | hghgh
      | Meagda      | Magedovna  | peep@gmail.com            | sds
      | jaeva       | geurus     | gureus@gmail.com          | debeunk
      | Feliseha    | Widmoere   | fwidmeore0@shareasale.com | TwBcPP
      | Anneabel    | Haccleton | ahacecleton1@va.gov       | w1POixUgM






#  @
#  Scenario Outline: Verify sign up russian and chinese names but english email
#    Given on login page
#    Then I click on sign up page
#    When on sign up page I fill out the fields: name "<FIRST NAME>", surname "<LAST NAME>" and email "<EMAIL ADDRESS>" and password "<PASSWORD>" click sign up button
#    Then I verify msg "Registration Successfull" or "This email already used"
#
#      | FIRST NAME   | LAST NAME   | EMAIL ADDRESS   | PASSWORD
#      | <FIRST NAME> | <LAST NAME> | <EMAIL ADDRESS> | <PASSWORD>
#    Examples:
#      | FIRST NAME | LAST NAME | EMAIL ADDRESS            | PASSWORD
#      | Таня       | Алегрова  | tania@gmail.com          | 1234599
#      | 雅哪        | 女        | gurus@gmail.com          | i8i8i







