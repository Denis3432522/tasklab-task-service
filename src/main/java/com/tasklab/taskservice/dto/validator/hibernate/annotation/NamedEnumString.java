package com.tasklab.taskservice.dto.validator.hibernate.annotation;

import com.tasklab.taskservice.dto.validator.hibernate.validator.NamedEnumStringValidator;
import com.tasklab.taskservice.enumeration.Named;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE_USE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NamedEnumStringValidator.class)
@ReportAsSingleViolation
@Documented
public @interface NamedEnumString {
    Class<? extends Named> value();
    String propertyName() default "";
    String message() default "value doesn't exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}