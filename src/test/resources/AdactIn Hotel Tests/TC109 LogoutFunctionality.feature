Feature: TC 109 Logout button functionality
  Scenario: As user I am logout from the application when I click logout button
    Given The hotel booking website is open
    When I complete username field
    And I complete password field
    And I press 'Login' button
    Then My credentials are verified and I am logged in
    When I set 'Sydney' location
    And I set 'Hotel Creek' hotel
    And I set the first room type
    And I set '2' as number of-rooms
    And I set today as check-in date
    And I set tomorrow as check-out date
    And I set '1' as number-of-adults
    And I set '0' as number-of-children
    And I press the 'Search' button
    And I select displayed hotel
    And I press continue button
    Then I am redirected to booking page
    When I enter first name
    And I enter second name
    And I enter billing address
    And I enter credit card No.
    And I select credit card type
    And I select expiry date
    And I enter CVV number
    And I press book now
    Then I am redirected to booking confirmation page
    When I click logout button
    Then I am logout from the application



