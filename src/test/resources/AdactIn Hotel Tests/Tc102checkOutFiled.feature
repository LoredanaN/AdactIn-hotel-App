Feature: Verify check-out later date than check-in date.
  Scenario: As a user I enter a check-out date later than check-in date
    Given I navigate to the application homepage
    When I enter the username
    And I enter the password
    And I click login
    Then I am logged in
    When I select a location
    And I select a hotel
    And I select a room type
    And I select no-of-rooms
    And I enter check-in date later than the check-out date
    Then Verify that system gives an error

