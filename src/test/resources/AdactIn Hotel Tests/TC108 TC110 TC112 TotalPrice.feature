Feature: TC 108/TC 110 The tests has the expected result and the same steps to verify Total price
  Scenario: I verify if total price is calculated as “price per night, no. of nights, no. of rooms”.
    Given I go to booking website
    When I use a valid username
    And I use a valid password
    And I click the 'Login' button
    Then Now I am successfully logged in
    When I select 'Sydney' location
    And I select 'Hotel Creek' hotel
    And I select the first room type
    And I select '2' as number of-rooms
    And I select today as check-in date
    And I select tomorrow as check-out date
    And I select '1' as number-of-adults
    And I select '0' as number-of-children
    And I click the 'Search' button
    And On 'Select hotel' page I select the hotel
    And I click continue button
    Then Total price is calculated as “price per night, no. of nights, no. of rooms”.
#  The test failed, total price isn't calculated as “price per night * no. of nights * no. of rooms”.Bug to be fixed

  Scenario: TC112 I check if total bill price is calculated as total price + 10% total price
    Given I go to booking website
    When I use a valid username
    And I use a valid password
    And I click the 'Login' button
    Then Now I am successfully logged in
    When I select 'Sydney' location
    And I select 'Hotel Creek' hotel
    And I select the first room type
    And I select '2' as number of-rooms
    And I select today as check-in date
    And I select tomorrow as check-out date
    And I select '1' as number-of-adults
    And I select '0' as number-of-children
    And I click the 'Search' button
    And On 'Select hotel' page I select the hotel
    And I click continue button
    Then I verify final billed price
 #     Final billed price is not the expected result: expected:<[275.0]> but was:<[148.5]>
