Feature: Checking if the 3% tax is applied correctly for a fractional price

  Scenario: Checking if the 3% tax is applied correctly for a fractional price
    Given User navigates to login page
    When User logs in with valid credentials
    And I click on attractions sidebar
    And I click on my attractions
    And I click on GoToHub button
    And I click on the Buy Now button
    And I click on ticket
    Then Check user is logged in
    And Check if tax is correct