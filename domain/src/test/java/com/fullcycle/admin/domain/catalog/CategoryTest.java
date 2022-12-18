package com.fullcycle.admin.domain.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CategoryTest {
    
    @Test
    public void givenValidParams_whenCallNewCatalgy_thenInstantiateACategory () {
        final String expectedName = "Filmes";
        final String expectedDescription = "A categoria mais assistida";
        final boolean expectedIsActive = true;

        var catalog = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        assertNotNull(catalog);
        assertEquals(expectedName, catalog.getName());
        assertEquals(expectedDescription, catalog.getDescription());
        assertEquals(expectedIsActive, catalog.isActive());
        assertNotNull(catalog.getCreatedAt());
        assertNotNull(catalog.getUpdatedAt());
        assertNull(catalog.getDeletedAt());
    }
}
