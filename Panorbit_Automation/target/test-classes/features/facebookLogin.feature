@login
Feature: Facebook Login functionality
  Description:- Login to facebook

  Background:
    Given enter the url

    Scenario: To check Login functionality of facebook with valid credentials
      When user enters valid username and password
      Then home page should be displayed
      And  update in the sheet

  Scenario: To check Login functionality of facebook with invalid credentials
    When user enters invalid username and password
    Then proper error message should be displayed
    And  update the status in the sheet
