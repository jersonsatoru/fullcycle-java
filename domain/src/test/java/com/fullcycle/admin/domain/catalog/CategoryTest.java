package com.fullcycle.admin.domain.catalog;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void givenValidCategory_whenCallDeactivate_thenReturnInactiveCategory() {
        final String expectedName = "Filmes";
        final String expectedDescription = "Um dos melhores, vale a pena conferir!";
        final boolean expectedIsActive = true;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));
        final var updatedAt = aCategory.getUpdatedAt();
        assertNull(aCategory.getDeletedAt());
        assertTrue(aCategory.isActive());
        final var currentCategory = aCategory.deactivate();

        assertEquals(expectedName, currentCategory.getName());
        assertEquals(expectedDescription, currentCategory.getDescription());
        assertFalse(currentCategory.isActive());
        assertNotNull(currentCategory.getCreatedAt());
        assertTrue(currentCategory.getUpdatedAt().isAfter(updatedAt));
        assertNotNull(currentCategory.getDeletedAt());
    }

    @Test
    public void givenAnInactiveCategory_whenCallActivate_thenReturnAnActiveCategory() {
        final String expectedName = "Filmes";
        final String expectedDescription = "Um dos melhores, vale a pena conferir!";
        final boolean expectedIsActive = true;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));
        final var deactiveCategory = aCategory.deactivate();
        final var updatedAt = deactiveCategory.getUpdatedAt();
        assertNotNull(deactiveCategory.getDeletedAt());
        assertFalse(deactiveCategory.isActive());

        final var activeCategory = aCategory.activate();
        assertEquals(expectedName, activeCategory.getName());
        assertEquals(expectedDescription, activeCategory.getDescription());
        assertTrue(activeCategory.isActive());
        assertNotNull(activeCategory.getCreatedAt());
        assertTrue(activeCategory.getUpdatedAt().isAfter(updatedAt));
        assertNull(activeCategory.getDeletedAt());
    }

    @Test
    public void givenAValidCategory_whenCallUpdate_thenReturnAnUpdatedCategory() {
        final String expectedName = "Filmes";
        final String expectedDescription = "A categoria mais assistida";
        final boolean expectedIsActive = true;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));
        final var createdAt = category.getCreatedAt();
        final var updatedAt = category.getUpdatedAt();

        final String expectedNewName = "Filmes Novos";
        final String expectedNewDescription = "A categoria mais assistida e inédita";
        final boolean expectedNewIsActive = true;
        final var updatedCategory = category.update(expectedNewName, expectedNewDescription, expectedNewIsActive);
        assertDoesNotThrow(() -> updatedCategory.validate(new ThrowsValidationHandler()));
        
        assertEquals(expectedNewName, updatedCategory.getName());
        assertEquals(expectedNewDescription, updatedCategory.getDescription());
        assertEquals(expectedNewIsActive, updatedCategory.isActive());
        assertEquals(createdAt, updatedCategory.getCreatedAt());
        assertTrue(updatedCategory.getUpdatedAt().isAfter(updatedAt));
        assertNull(updatedCategory.getDeletedAt());
    }

    @Test
    public void givenAValidCategory_whenCallUpdateWithActiveEqualsFalse_thenReturnAnUpdatedCategory() {
        final String expectedName = "Filmes";
        final String expectedDescription = "A categoria mais assistida";
        final boolean expectedIsActive = true;

        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));
        final var createdAt = category.getCreatedAt();
        final var updatedAt = category.getUpdatedAt();

        final String expectedNewName = "Filmes Novos";
        final String expectedNewDescription = "A categoria mais assistida e inédita";
        final boolean expectedNewIsActive = false;
        assertTrue(category.isActive());
        assertNull(category.getDeletedAt());

        final var updatedCategory = category.update(expectedNewName, expectedNewDescription, expectedNewIsActive);
        assertDoesNotThrow(() -> updatedCategory.validate(new ThrowsValidationHandler()));
        
        assertEquals(expectedNewName, updatedCategory.getName());
        assertEquals(expectedNewDescription, updatedCategory.getDescription());
        assertFalse(updatedCategory.isActive());
        assertEquals(createdAt, updatedCategory.getCreatedAt());
        assertTrue(updatedCategory.getUpdatedAt().isAfter(updatedAt));
        assertNotNull(updatedCategory.getDeletedAt());
    }
}
