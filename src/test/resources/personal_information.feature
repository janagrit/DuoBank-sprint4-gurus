
Feature: Personal Details Page verification

  Background:
    Given on login page
    When I am on login page I filling out email and password and click login button
    When I am on the main page clicking on Mortgage Application

  @run
  Scenario: filling personal details
    Then I am on on the preapproval page filling a name "Vasia", purchase price 10000, down payment 1000 and click next button
    Then I am on the personal information page put name "Alice", surname "Abe", email "email@gmail.com", social 1111111111, phone 111111111


