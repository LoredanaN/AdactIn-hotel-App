Feature: Hotel App login
  Scenario: As a user I am able to login in to the hotel application
    Given I go to the hotel application page
    When I type valid username
    And I type a valid password
    And I click login button
    Then I am successfully logged in
