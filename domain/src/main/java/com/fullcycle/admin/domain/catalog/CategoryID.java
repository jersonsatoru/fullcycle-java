package com.fullcycle.admin.domain.catalog;

import java.util.Objects;
import java.util.UUID;

import com.fullcycle.admin.domain.Identifier;

public class CategoryID extends Identifier {
    private final String value;

    public String getValue() {
        return value;
    }

    private CategoryID(final String value) {
        Objects.requireNonNull(value, "Must be not null");
        this.value = value;
    }

    public static CategoryID unique() {
        return new CategoryID(UUID.randomUUID().toString().toLowerCase());
    }

    public static CategoryID from(final String anId) {
        return new CategoryID(anId);
    }

    public static CategoryID from(final UUID anId) {
        return new CategoryID(anId.toString().toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final CategoryID entity = (CategoryID) obj;
        return getValue().equals(entity.getValue());
    }
}
