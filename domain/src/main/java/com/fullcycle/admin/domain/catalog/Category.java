package com.fullcycle.admin.domain.catalog;

import java.time.Instant;
import com.fullcycle.admin.domain.AggregateRoot;
import com.fullcycle.admin.domain.validation.ValidationHandler;

public class Category extends AggregateRoot<CategoryID>{
    private String name;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
    
    public CategoryID getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }


    public boolean isActive() {
        return active;
    }


    public Instant getCreatedAt() {
        return createdAt;
    }


    public Instant getUpdatedAt() {
        return updatedAt;
    }


    public Instant getDeletedAt() {
        return deletedAt;
    }


    private Category(
        final CategoryID id, 
        final String name, 
        final String description, 
        final boolean active, 
        final Instant createdAt, 
        final Instant updatedAt,
        final Instant deletedAt
    ) {
        super(id);
        this.name = name;
        this.description = description;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }


    public static Category newCategory (
        final String name, 
        final String description,
        final boolean isActive
    ) {
        var id = CategoryID.unique();
        return new Category(
            id,
            name,
            description,
            isActive,
            Instant.now(),
            Instant.now(),
            null
        );
    }


    @Override
    public void validate(final ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();

    }
}
