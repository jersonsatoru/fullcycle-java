package com.fullcycle.admin.domain;

import java.util.Objects;

import com.fullcycle.admin.domain.validation.ValidationHandler;

public abstract class Entity<ID extends Identifier> {
    protected final ID id;

    public ID getId() {
        return id;
    }

    protected Entity(final ID id) {
        Objects.requireNonNull(id, "Must be not null");
        this.id = id;
    }

    public abstract void validate(ValidationHandler validator);

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final Entity<?> entity = (Entity<?>) obj;
        return getId().equals(entity.getId());
    }
}
