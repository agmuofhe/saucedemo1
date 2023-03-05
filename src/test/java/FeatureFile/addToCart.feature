Feature: Sauce Demo Checkout Functionality

  Scenario:Navigate to store and checkout
    Given I've logged in using my credentials
    When I click on Add to cart button for the Sauce Labs Backpack
    Then Add to cart button should change to remove Backpack
    When I click on Add to cart button for the Sauce Labs Onesie
    Then Add to cart button should change to remove Onesie
    When I click on the Shopping cart Icon
    Then I should be directed to cart page
    Then The product on the cart should be Sauce Labs Backpack and Sauce Labs Onesie
    When I click on Checkout button
    Then I should be directed to contact information page
    When I Enter the the "abcd" "qwerty" and "10000"
    And I click continue button
    Then I should be directed to Checkout: Overview
    And The Total should be "$41.02"
    When I click Finish
    Then I should be directed to Checkout:Complete screen

