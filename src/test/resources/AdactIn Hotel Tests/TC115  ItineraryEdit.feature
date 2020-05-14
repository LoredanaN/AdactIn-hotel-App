Feature: Itinerary details are not editable
  Scenario: TC 115 I verify whether the booked itinerary details are not editable.
    Given I am on hotel booking website logged in
    When I select Adelaide as location
    And I select hotel Cornice
    And I set 'standard' room
    And I set number of rooms
    And I set check-in date and check-out date
    And I set number of adults and number of children
    And I press Search
    Then The Select hotel page is displayed
    When I click on displayed hotel
    And I click continue
    Then I am on book a hotel page
    When I enter personal details and credit card details
    And I press 'book now' button
    Then I am on booking confirmation page
    When I click on 'My itinerary' button
    Then I verify if whether the booked itinerary details are not editable.
#    Itinerary details are editable, but details once accepted should not be editable.


