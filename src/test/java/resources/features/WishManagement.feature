Feature: Wish Management

  Scenario: Successfully creating a wish
    Given an existing collection with ID 1
    When I create a wish titled "Nintendo Switch"
    Then the wish should be saved correctly with title "Nintendo Switch"