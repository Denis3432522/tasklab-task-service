package com.tasklab.taskservice.dto.validator.hibernate.validator;

import com.tasklab.taskservice.dto.validator.hibernate.annotation.NamedEnumString;
import com.tasklab.taskservice.enumeration.Named;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NamedEnumStringValidator implements ConstraintValidator<NamedEnumString, String> {

    private List<String> valueList;
    private String messageTemplate;

    @Override
    public void initialize(NamedEnumString constraintAnnotation) {
        if(!constraintAnnotation.value().isEnum())
            throw new IllegalArgumentException(constraintAnnotation.value().getSimpleName() + " is not enum");

        valueList = Arrays.stream(constraintAnnotation.value().getEnumConstants())
                .map(Named::getName)
                .collect(Collectors.toList());

        String propertyName = constraintAnnotation.propertyName();
        messageTemplate = propertyName + (!propertyName.isEmpty() ? " " : "") + "? doesn't exist";
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null || valueList.contains(s))
            return true;

        String msg = messageTemplate.replaceAll("\\?", s);

        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
        return false;
    }
}
