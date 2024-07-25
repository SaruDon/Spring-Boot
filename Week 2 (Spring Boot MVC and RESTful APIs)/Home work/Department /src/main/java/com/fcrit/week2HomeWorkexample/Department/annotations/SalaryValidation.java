package com.fcrit.week2HomeWorkexample.Department.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(
        validatedBy = SalaryValidator.class
)
public @interface SalaryValidation {
    String message() default "Id must be prime";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
