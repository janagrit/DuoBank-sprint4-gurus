@db_Sprint_6
Feature: Check DB information

  Scenario: Check for duplicate values in the username column
    Given I am connected to the DB
    When I send a query to check for duplicate usernames
    Then The returned result list should be empty
#    Then The returned result list should be empty but it is not

  Scenario: Verify the column length for name column of the playlists table

    Given I am connected to the DB
    When I update the name column with a String with an invalid length of 2000, the update should truncate the length to 1000
#    When I update the name column with a String with an invalid length of 2000, the update should truncate the length to 1000 but it is not