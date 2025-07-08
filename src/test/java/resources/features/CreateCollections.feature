Feature: Create a collection

  Scenario: Successfully create a collection with a valid title
    When create a collection with the name "My Wishlist"
    Then the collection should exist with the title "My Wishlist"