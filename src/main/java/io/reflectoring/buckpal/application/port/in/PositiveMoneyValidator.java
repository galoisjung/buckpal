package io.reflectoring.buckpal.application.port.in;

import io.reflectoring.buckpal.application.domain.model.Money;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PositiveMoneyValidator implements ConstraintValidator<PositiveMoney, Money> {
    @Override
    public boolean isValid(Money money, ConstraintValidatorContext constraintValidatorContext) {
        return money.isPositiveOrZero();
    }
}
