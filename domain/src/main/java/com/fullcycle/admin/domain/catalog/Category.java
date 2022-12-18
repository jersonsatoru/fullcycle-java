package com.fullcycle.admin.domain.catalog;

import java.time.Instant;
import java.util.UUID;

public class Category {
    private String id;
    private String name;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    
    public String getId() {
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
        final String id, 
        final String name, 
        final String description, 
        final boolean active, 
        final Instant createdAt, 
        final Instant updatedAt,
        final Instant deletedAt
    ) {
        this.id = id;
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
        var id = UUID.randomUUID().toString();
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
}
