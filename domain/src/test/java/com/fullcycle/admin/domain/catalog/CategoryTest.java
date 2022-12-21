package com.fullcycle.admin.domain.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.fullcycle.admin.domain.exceptions.DomainException;
import com.fullcycle.admin.domain.validation.handler.ThrowsValidationHandler;

public class CategoryTest {
    
    @Test
    public void givenValidParams_whenCallNewCategory_thenInstantiateACategory() {
        final String expectedName = "Filmes";
        final String expectedDescription = "A categoria mais assistida";
        final boolean expectedIsActive = true;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        assertNotNull(category);
        assertEquals(expectedName, category.getName());
        assertEquals(expectedDescription, category.getDescription());
        assertEquals(expectedIsActive, category.isActive());
        assertNotNull(category.getCreatedAt());
        assertNotNull(category.getUpdatedAt());
        assertNull(category.getDeletedAt());
    }

    @Test
    public void givenAnNullName_whenCallNewCategoryAndValidate_thenReceiveError() {
        final String expectedName = null;
        final String expectedDescription = "A categoria mais assistida";
        final boolean expectedIsActive = true;

        final var expectedErrorMessages = "'name' must not be null";
        final var expectedErrorCount = 1;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        
        final var actualException = assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessages, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnEmptyName_whenCallNewCategoryAndValidate_thenReceiveError() {
        final String expectedName = " ";
        final String expectedDescription = "A categoria mais assistida";
        final boolean expectedIsActive = true;

        final var expectedErrorMessages = "'name' must not be empty";
        final var expectedErrorCount = 1;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        
        final var actualException = assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessages, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidName_whenCallNewCategoryAndValidate_thenReceiveError() {
        final String expectedName = "AB";
        final String expectedDescription = "A categoria mais assistida";
        final boolean expectedIsActive = true;

        final var expectedErrorMessages = "'name' must be greater than or equals than 3 or less than or equals 255";
        final var expectedErrorCount = 1;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        
        final var actualException = assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessages, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnNullDescription_whenCallNewCategoryAndValidate_thenReceiveError() {
        final String expectedName = "Filmes";
        final String expectedDescription = null;
        final boolean expectedIsActive = true;

        final var expectedErrorMessages = "'description' must not be null";
        final var expectedErrorCount = 1;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        
        final var actualException = assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessages, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnEmptyDescription_whenCallNewCategoryAndValidate_thenReceiveError() {
        final String expectedName = "Filmes";
        final String expectedDescription = " ";
        final boolean expectedIsActive = true;

        final var expectedErrorMessages = "'description' must not be empty";
        final var expectedErrorCount = 1;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        
        final var actualException = assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessages, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidDescription_whenCallNewCategoryAndValidate_thenReceiveError() {
        final String expectedName = "Filmes";
        final String expectedDescription = "AS";
        final boolean expectedIsActive = true;

        final var expectedErrorMessages = "'description' must be greater than or equals than 3 or less than or equals 255";
        final var expectedErrorCount = 1;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        
        final var actualException = assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessages, actualException.getErrors().get(0).message());
    }
}
