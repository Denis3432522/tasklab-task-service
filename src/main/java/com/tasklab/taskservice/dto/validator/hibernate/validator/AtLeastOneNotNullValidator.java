package com.tasklab.taskservice.dto.validator.hibernate.validator;

import com.tasklab.taskservice.dto.response.error.ErrorResponse;
import com.tasklab.taskservice.dto.validator.hibernate.annotation.AtLeastOneNotNull;
import com.tasklab.taskservice.exception.InternalServerErrorException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class AtLeastOneNotNullValidator implements ConstraintValidator<AtLeastOneNotNull, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        if(obj == null)
            return true;

        Field[] fields = obj.getClass().getDeclaredFields();

        try {
            for (var field : fields) {
                field.setAccessible(true);
                if (field.get(obj) != null)
                    return true;
            }
        } catch (Exception e) {
            throw new InternalServerErrorException(new ErrorResponse(e.getMessage()), e);
        }

        return false;
    }
}
