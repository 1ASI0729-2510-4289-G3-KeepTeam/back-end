package com.acme.keeplo.platform.unit.tests;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WishTests {
    @Test
    public void createWish_WithValidData_ShouldSetFieldsCorrectly() {
        // Arrange
        Collection collection = new Collection("Gifts", false, null, 1L);

        // Act
        Wish wish = new Wish("New Laptop", "Gaming laptop", "https://todotec.com.pe/products/laptop-hp-15-fc0012la-ryzen7-7730u-16gb-ssd-512gb-15-6-freedos-802n1la", collection, false, "https://todotec.com.pe/cdn/shop/files/HP-Laptop-15-fc0012la_1.png");

        // Assert
        assertEquals("New Laptop", wish.getTitle());
        assertEquals("Gaming laptop", wish.getDescription());
        assertEquals("https://todotec.com.pe/products/laptop-hp-15-fc0012la-ryzen7-7730u-16gb-ssd-512gb-15-6-freedos-802n1la", wish.getRedirectUrl());
        assertFalse(wish.getIsInTrash());
        assertEquals("https://todotec.com.pe/cdn/shop/files/HP-Laptop-15-fc0012la_1.png", wish.getUrlImg());
        assertEquals(collection, wish.getCollection());
    }

    @Test
    public void updateWish_ShouldChangeFields() {

        // Arrange
        Collection collection = new Collection("Electronics", false, null, 1L);
        Wish wish = new Wish("Phone", "Old phone", "url", collection, false, "img");

        // Act
        wish.update("Smartphone", "new-url", "new desc", "new-img", true);

        // Assert
        assertEquals("Smartphone", wish.getTitle());
        assertEquals("new desc", wish.getDescription());
        assertEquals("new-url", wish.getRedirectUrl());
        assertEquals("new-img", wish.getUrlImg());
        assertTrue(wish.getIsInTrash());
    }
}
