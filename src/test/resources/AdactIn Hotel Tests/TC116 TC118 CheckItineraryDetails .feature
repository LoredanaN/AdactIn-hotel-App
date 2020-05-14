Feature: TC116 TC118 Itinerary should reflect the correct information in line with the booking.
  Scenario: I check whether the booked itinerary reflects the correct information in line with the booking.
    Given I am on website application logged in
    When I set the hotel and I set the location
    And I choose the type of room and number of rooms
    And I select the dates of check-in and check-out
    And I choose the adults and children number
    Then For searching,I click Search button
    When I want to book a hotel,I select the radio button besides the displayed hotel and I click 'continue'
    And I complete 'Book a hotel' page with personal details and I click 'Book now' button
    Then The booking confirmation page is displayed
    When I verify the details from booking confirmation page and press 'My itinerary' button
    And Booked itinerary page is displayed
    Then I check whether the booked itinerary reflects the correct information in line with the booking
#    In booking confirmation page last name and number of days are not displayed: expected:<[]> but was:<[Apuchinesei]> bug to be fixed!
#   The room type is not the same in booked itinerary page as in booking confirmation page. Bug to be fixed:org.junit.ComparisonFailure: expected:<[Deluxe]> but was:<[Standard]>
#  Total price is not the same in booked itinerary page as in booking confirmation page :expected:<AUD $ 14[8.5]> but was:<AUD $ 14[9]>

