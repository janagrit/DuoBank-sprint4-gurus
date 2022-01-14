Feature: Login API features testing



  @api
  Scenario: Verify successful POST /login.php endpoint

    Given I send POST "/login.php" request as a valid user with email "duotechb5@gmail.com" and password "duotechb5"
    Then The status code should be 200
    And The response body should contain the confirmation message "You have successfully logged in."




  @api
  Scenario: Test POST /login.php endpoint with Invalid password/email

    Given I send POST "/login.php" request as an invalid user with email "duotechb5@gmail.com" and password "duo"
    Then The status code should be 200
    And The response body should contain the informative message "Invalid Email Address!"















