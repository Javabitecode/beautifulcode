package ru.sber.beautifulcode.textchecker.model.impl;

import lombok.Builder;
import lombok.Data;
import ru.sber.beautifulcode.textchecker.model.ConstraintViolation;

@Data
@Builder
public class ConstraintViolationImpl implements ConstraintViolation {
    private boolean valid;
    private String message;
}