Feature: TC 105 Verify check-in date and check-out date
  Scenario: I verify if check-in date and check-out date in search hotel are the same as in select hotel
  Given I am to the hotel application page
  When I enter my username
  And I enter my password
  And I click the login button
  Then I am now successfully logged in
    When I select location Sydney
    And I select hotel Hotel Creek
    And I select room type Standard
    And I select number of-rooms 1
    And I select check-in date
    And I select check-out date
    And I select no-of-adults
    And I select no-of-children
    And I click Search button
    Then The check-in date and check-out date in search hotel are the same as in select hotel

