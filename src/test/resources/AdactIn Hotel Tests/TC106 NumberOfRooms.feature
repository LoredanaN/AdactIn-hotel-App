Feature: TC 105 Verify check-in date and check-out date
  Scenario: I verify if number of rooms in search hotel is the same as in select hotel
    Given I go to the hotel page
    When I type my username
    And I type my password
    And I click log in button
    Then I am now logged in
    When I select location 'Sydney'
    And I select hotel 'Hotel Creek'
    And I select room type 'Standard'
    And I select number of-rooms '3'
    And I select checkin date
    And I select checkout date
    And I select number-of-adults
    And I select number-of-children
    And I click the Search button
    Then The number of rooms in search hotel is the same as in select hotel