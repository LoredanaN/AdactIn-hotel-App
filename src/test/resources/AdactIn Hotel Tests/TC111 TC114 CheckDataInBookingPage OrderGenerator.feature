Feature: Check Booking confirmation page
Scenario: TC111 I check if data in Booking confirmation page is the same as selected in previous screen
  Given The booking website is open
When I complete the username field with valid data
And I complete the password field with valid data
And I press Login button
Then With my credentials I am logged in
When I set Sydney location
And I set Hotel Creek hotel
And I set the room type
And I set the number of-rooms
And I set the check-in date
And I set the check-out date
And I set the number-of-adults
And I set the number-of-children
And I press the Search button
And I choose first hotel option
And I press 'Continue' button
Then The booking page is displayed
 When I enter the first name
  And I enter the last name
  And I enter the billing address
  And I enter the credit card number
  And I select the credit card type
  And I select the expiry date
  And I enter the CVV number
  And I click book now
  Then I check the hotel name
  When I check the location
  And I check the room type
  Then I check total price
#Room type is not the same in booking confirmation page as in previous page!


  Scenario: TC114 I verify if order number is generated in booking confirmation page
    Given The booking website is open
    When I complete the username field with valid data
    And I complete the password field with valid data
    And I press Login button
    Then With my credentials I am logged in
    When I set Sydney location
    And I set Hotel Creek hotel
    And I set the room type
    And I set the number of-rooms
    And I set the check-in date
    And I set the check-out date
    And I set the number-of-adults
    And I set the number-of-children
    And I press the Search button
    And I choose first hotel option
    And I press 'Continue' button
    Then The booking page is displayed
    When I enter the first name
    And I enter the last name
    And I enter the billing address
    And I enter the credit card number
    And I select the credit card type
    And I select the expiry date
    And I enter the CVV number
    And I click book now
    Then I check the hotel name
    When I check the location
    And I check the room type
   Then I check total price
    Then I verify if order number is generated



