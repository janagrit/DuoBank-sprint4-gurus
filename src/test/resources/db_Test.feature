@db_Sprint_6
Feature: Check DB information

  Scenario: Check for duplicate values in the first name column
#    Given I am connected to the DB
    When I send a query to check for duplicate usernames
    Then The returned result list should be empty
#    Then The returned result list should be empty but it is not

  Scenario: Verify the column length for name column of the tbl_mortagage table

#    Given I am connected to the DB
    When I update the name column with a String with an invalid length of 2000, the update should truncate the length to 1000
#    When I update the name column with a String with an invalid length of 2000, the update should truncate the length to 1000 but it is not

  @db_Sprint_6
  Scenario: Check for duplicate values in the cell phone column
#    Given I am connected to the DB
    When I send a query to check for duplicate cell phone
    Then The returned result list should be empty
  @db_Sprint_6
  Scenario: Verify the est_purchase_price in the tbl_mortagage table accept letters instead of only numbers
#    Given I am connected to the DB
    When I enter the letter and as an estimate purchase price and it will still be accepted
  @db_Sprint_6
  Scenario: Verify the unicode support for name column of the playlists table
#    Given I am connected to the DB
    When I update the name column with a unicode chars, the update should be successful
    Then The update should be also successful on the UI
