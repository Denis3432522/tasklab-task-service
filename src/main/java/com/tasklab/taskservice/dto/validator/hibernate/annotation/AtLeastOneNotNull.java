package com.tasklab.taskservice.dto.validator.hibernate.annotation;

import com.tasklab.taskservice.dto.validator.hibernate.validator.AtLeastOneNotNullValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AtLeastOneNotNullValidator.class)
@ReportAsSingleViolation
@Documented
public @interface AtLeastOneNotNull {
    String message() default "At least one field must be not null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
