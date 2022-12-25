package com.fullcycle.admin.domain.catalog;

import com.fullcycle.admin.domain.validation.ValidationHandler;
import com.fullcycle.admin.domain.validation.Validator;
import com.fullcycle.admin.domain.validation.Error;

public class CategoryValidator extends Validator {

    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 255;
    private final Category category;

    public CategoryValidator(final Category category, final ValidationHandler handler) {
        super(handler);
        this.category = category;
    }

    @Override
    public void validate() {
        this.checkNameConstraints();
        this.checkDescriptionConstraints();
    }

    private void checkNameConstraints() {
        if (this.category.getName() == null) {
            this.validationHandler().append(new Error("'name' must not be null"));
        }
        if (this.category.getName().isBlank()) {
            this.validationHandler().append(new Error("'name' must not be empty"));
        }
        if (this.category.getName().length() > MAX_LENGTH || this.category.getName().length() < MIN_LENGTH) {
            this.validationHandler().append(new Error("'name' must be greater than or equals than 3 or less than or equals 255"));
        }
    }

    private void checkDescriptionConstraints() {
        if (this.category.getDescription() == null) {
            this.validationHandler().append(new Error("'description' must not be null"));
        }
        if (this.category.getDescription().isBlank()) {
            this.validationHandler().append(new Error("'description' must not be empty"));
        }
        if (this.category.getDescription().length() > MAX_LENGTH || this.category.getDescription().length() < MIN_LENGTH) {
            this.validationHandler().append(new Error("'description' must be greater than or equals than 3 or less than or equals 255"));
        }
    }
}
