Feature: Location from Select hotel is same as location from Search hotel
  Scenario: I verify that the location selected in Search hotel is the same as the location displayed in Select hotel
    Given I am on the website hotel page logged in
    When I choose the location
    And I choose the hotel
    And I choose the room type
    And I choose the numbers of rooms
    And I set the check-in date and check-out date
    And I select number of adults
    And I select the number of children
    And I click on Search button
    Then The location displayed in Select hotel is the same as the location in Search hotel