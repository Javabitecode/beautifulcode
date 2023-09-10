package ru.sber.beautifulcode.textchecker.validator.text.impl;

import lombok.Builder;
import lombok.Data;
import ru.sber.beautifulcode.textchecker.validator.ConstraintViolation;

@Data
@Builder
public class ConstraintViolationImpl implements ConstraintViolation {
    private boolean valid;
}