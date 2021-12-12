Feature:  As a user I should be able to sign up and use my credentials later on

  Scenario Outline: Verify sign up feature using proper sets of data
    Given on login page
    Then I click on sign up page
    When on sign up page I fill out the fields: name "<FIRST NAME>", surname "<LAST NAME>" and email "<EMAIL ADDRESS>" and password "<PASSWORD>" click sign up button
    Then I verify msg "Registration Successfull" or "This email already used"

      | FIRST NAME   | LAST NAME   | EMAIL ADDRESS   | PASSWORD
      | <FIRST NAME> | <LAST NAME> | <EMAIL ADDRESS> | <PASSWORD>
    Examples:
      | FIRST NAME | LAST NAME | EMAIL ADDRESS            | PASSWORD
      | Olya       | Karenina  | Anna@gmail.com           | 111111
      | Magda      | Magdovna  | pep@gmail.com            | 9930303
      | java       | gurus     | gurus@gmail.com          | debunk
      | Felisha    | Widmore   | fwidmore0@shareasale.com | TwBcPP
      | Annabel    | Haccleton | ahaccleton1@va.gov       | w1POixUgM


@temp
  Scenario Outline: Verify sign up russian and chinese names but english email
    Given on login page
    Then I click on sign up page
    When on sign up page I fill out the fields: name "<FIRST NAME>", surname "<LAST NAME>" and email "<EMAIL ADDRESS>" and password "<PASSWORD>" click sign up button
    Then I verify msg "Registration Successfull" or "This email already used"

      | FIRST NAME   | LAST NAME   | EMAIL ADDRESS   | PASSWORD
      | <FIRST NAME> | <LAST NAME> | <EMAIL ADDRESS> | <PASSWORD>
    Examples:
      | FIRST NAME | LAST NAME | EMAIL ADDRESS            | PASSWORD
      | Таня       | Алегрова  | tania@gmail.com          | 1234599
      | 雅哪        | 女        | gurus@gmail.com          | i8i8i





#
#  Scenario: Verify products details that are on sale
#    Given on login page
#    When I click on on products that are on sale their expected values should be correct according to the given excel file
#
#


