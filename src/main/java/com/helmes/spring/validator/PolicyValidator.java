package com.helmes.spring.validator;

import com.helmes.spring.model.Policy;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class PolicyValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Policy.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Policy policy = (Policy) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty", "Field is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type.id", "NotEmpty", "Field is required.");
    }
}
