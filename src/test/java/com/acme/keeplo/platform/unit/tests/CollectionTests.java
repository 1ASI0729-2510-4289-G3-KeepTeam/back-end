package com.acme.keeplo.platform.unit.tests;
import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionTests {

    @Test
    public void createCollection_WithValidData_ShouldSetFieldsCorrectly() {
        // Arrange
        String title = "Dog Things";
        boolean isInTrash = false;
        Long parentId = null;
        Long userId = 1L;

        // Act
        Collection collection = new Collection(title, isInTrash, parentId, userId);

        // Assert
        assertEquals(title, collection.getTitle());
        assertFalse(collection.isInTrash());
        assertNull(collection.getIdParentCollection());
        assertEquals(userId, collection.getIdUser());
    }

    @Test
    public void updateCollection_ShouldUpdateFields() {
        // Arrange
        Collection collection = new Collection("Old Name", false, null, 1L);

        // Act
        collection.update("New Name", true, 2L);

        // Assert
        assertEquals("New Name", collection.getTitle());
        assertTrue(collection.isInTrash());
        assertEquals(2L, collection.getIdParentCollection());
    }
}
