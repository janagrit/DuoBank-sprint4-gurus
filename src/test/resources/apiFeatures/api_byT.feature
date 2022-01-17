@api
Feature: API related features

  @api @smoke
  Scenario: Create a New User using POJO Class
    Given I add the headers "Content-Type", "application/json" and "Accept", "application/json"
    And I create a fake credentials using POJO class
    When I POST newly created user
    Then I verify the status code should be not greater than 300
    And The success "message" should be "You have successfully registered."



  @api
  Scenario: Login with valid credentials using POJO Class and Verify Status
    Given I add the headers "Accept" "application/json"
    When I POST info from POJO class to "/login.php" path
    Then The response status code should be 200





  @api @smoke
  Scenario: Test GET request for the mortgage application under valid admin credentials
    Given I am authorized at endpoint "/login.php"
    Given I add the header "Authorization" "token"
    When I send a GET request to "/getmortagage.php" endpoint
    Then The response status code should be 200


  @api @smoke
  Scenario: Test POST request for the specific mortgage application by its ID number
    Given I am authorized at endpoint "/login.php"
    Given Valid id number at the endpoint "/getmortagage.php"
    When I send a POST request to "/mortagagedetails.php" endpoint using a valid id number
    Then The response status code should be 200

  @api
  Scenario: Test GET request for the mortgage application under valid user credentials
    Given I am authorized as a user at endpoint "/login.php"
    When  I send a GET request to "/getmortagage.php" endpoint with user token
    Then The response status code should be 200
    Then The id should be 636
    Then The name should be "Barbie"

  @api
  Scenario: Test GET request for the mortgage application under admin credentials without valid token
    Given I am authorized at endpoint "/login.php"
    When I send a GET request to "/getmortagage.php" endpoint without a valid token
    Then The body status should be 401
    Then The body message should be "Unauthorized"

  @api @smoke
  Scenario: Test POST request for the application details of the logged in user
    Given I am authorized as a user at endpoint "/login.php"
    When I send a POST request to "/mortagagedetails.php" endpoint
    Then The response status code should be 200
    Then The body should contain "single_application"

