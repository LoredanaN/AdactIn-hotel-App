Feature: TC 107 Verify Room type
  Scenario: I verify if the room type in search hotel is the same as in select hotel
    Given I navigate to the hotel homepage
    When I use my username
    And I use my password
    And I click 'Login' button
    Then Now I am logged in
    When I select the first location
    And I select the first hotel
    And I select 'Deluxe' room type
    And I select the number of-rooms
    And I select the check-in date
    And I select the check-out date
    And I select the number-of-adults
    And I select the number-of-children
    And I click 'Search' button
    Then The room type in search hotel is the same as in select hotel