package com.fullcycle.admin.domain.catalog;

import com.fullcycle.admin.domain.validation.ValidationHandler;
import com.fullcycle.admin.domain.validation.Validator;
import com.fullcycle.admin.domain.validation.Error;

public class CategoryValidator extends Validator {

    private final Category category;

    public CategoryValidator(final Category category, final ValidationHandler handler) {
        super(handler);
        this.category = category;
    }

    @Override
    public void validate() {
        if (this.category.getName() == null) {
            this.validationHandler().append(new Error("'name' must not be null"));
        }
    }
}
