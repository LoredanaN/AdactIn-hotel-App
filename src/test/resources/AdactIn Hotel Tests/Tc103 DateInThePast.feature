Feature: Check-out date from the past
  Scenario: As a user I enter a date from the past in check-out date field
    Given I am on the website page and I am logged in
    When I select the location
    And I select the hotel
    And I select the room type
    And I select the numbers of rooms
    And I type check-out date and check-in date from the past
    Then An error message is displayed