Feature:  As a user I should be able to sign up and use my credentials later on



  Scenario Outline: Verify sign up feature using different sets of data
    Given on login page
    Then I click on sign up page
    When on sign up page I fill out the fields: name "<FIRST NAME>", surname "<LAST NAME>" and email "<EMAIL ADDRESS>" and password "<PASSWORD>" click sign up button
    Then I verify confirmation msg "Registration Successfull"

      | FIRST NAME   | LAST NAME   | EMAIL ADDRESS   | PASSWORD
      | <FIRST NAME> | <LAST NAME> | <EMAIL ADDRESS> | <PASSWORD>
    Examples:
      | FIRST NAME  | LAST NAME   | EMAIL ADDRESS      | PASSWORD
      | Olya       | Karenina  | Anna@gmail.com     | 111111
      | Magda      | Magdovna  | pep@gmail.com      | 9930303
      | Таня       | Алегрова  | Алегрова@gmail.com | 99
      | java       | gurus     | gurus@gmail.com    | debunk
      | 雅哪        | 女        | gurus@gmail.com    | i8i8i


  @test
  Scenario Outline: Verify login feature
    Given on login page
    Then filling the email "<EMAIL ADDRESS>" and password "<PASSWORD>" and click Login button
      | EMAIL ADDRESS   | PASSWORD
      | <EMAIL ADDRESS> | <PASSWORD>

    Examples:
      |  EMAIL ADDRESS      | PASSWORD
      | Anna@gmail.com      | 111111



