package com.example.order.web.validators;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.order.web.CreateOrderParameters;

public class CreateOrderParametersValidator
        implements ConstraintValidator<ValidCreateOrderParameters, CreateOrderParameters> {

    @Override
    public boolean isValid(CreateOrderParameters parameters, ConstraintValidatorContext context) {
        boolean result = true;

        if (Objects.isNull(parameters.getPlates()) || parameters.getPlates().length == 0) {
            context.buildConstraintViolationWithTemplate("Plates are required")
                    .addPropertyNode("plates")
                    .addConstraintViolation();
            result = false;
        }

        if (Objects.isNull(parameters.getDrinks()) || parameters.getDrinks().length == 0) {
            context.buildConstraintViolationWithTemplate("Drinks are required")
                    .addPropertyNode("drinks")
                    .addConstraintViolation();
            result = false;
        }

        if (Objects.isNull(parameters.getOrderType()) || parameters.getOrderType().isEmpty()) {
            context.buildConstraintViolationWithTemplate("Order type are required")
                    .addPropertyNode("orderType")
                    .addConstraintViolation();
            result = false;
        }

        return result;
    }

}