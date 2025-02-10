@Case2
Feature: Akakce Mobile Application Search and Filtering

  @case2
  Scenario Outline: Search and filter for a laptop in Akakce app
    Given User launches the Akakce mobile application
    When User proceeds with the continue without login option if the login prompt appears
    And User enters "<SearchKey>" in the search bar and initiates a search
    And User clicks on the Filter button
    And User selects Computer → Hardware under the subcategory and clicks on the View Products button
    And User selects the sorting option Highest Price
    And User clicks on the 10th product from the results
    And User clicks on the Go to Product button
    Then User should see the Go to Seller button displayed on the product details screen

    Examples:
    |SearchKey|
    | Laptop  |