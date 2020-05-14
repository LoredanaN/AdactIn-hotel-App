Feature: TC 117 TC119 TC120 While you are in Booked itinerary page you can search an order ID and you can cancel an order ID
  Title of each page should reflect its objective and the buttons should redirect as specified, to the relevant page.

  Scenario: TC117 I check whether “search order id” query is working and displaying the relevant details.
Given The website application is opened and I am logged in.
    When I am on Search hotel page I select location and the hotel
    And I choose room type and number of rooms
    And I set the date for check-in and check-out
    And I choose number of adults and children and I click Search button
    Then The 'Select hotel' page is displayed
    When I select the radio button besides the hotel entry and I click continue
    And I enter the personal details on Book a hotel page and I click Book now button
    Then The confirmation page is loaded
    When I press My Itinerary button
    And I am on Booked itinerary page
    And I type on 'Search Order Id' field an order id
    And I click 'Go' button
    Then The order id searched is displayed with the relevant details.

    Scenario:TC119 I verify that the order gets cancelled after click on Cancel order number link
      Given The website application is opened and I am logged in.
      When I am on Search hotel page I select location and the hotel
      And I choose room type and number of rooms
      And I set the date for check-in and check-out
      And I choose number of adults and children and I click Search button
      Then The 'Select hotel' page is displayed
      When I select the radio button besides the hotel entry and I click continue
      And I enter the personal details on Book a hotel page and I click Book now button
      Then The confirmation page is loaded
      When I press My Itinerary button
      And I am on Booked itinerary page
      And I type on 'Search Order Id' field an order id
      And I click 'Go' button
      Then The order id searched is displayed with the relevant details.
      When I click on Cancel <Order Number>
      And I click Yes on pop-up which asks where to cancel order or not
      Then I verify that order number is cancelled and no longer exists in Booked Itinerary page