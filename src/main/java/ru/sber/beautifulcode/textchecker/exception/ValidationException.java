package ru.sber.beautifulcode.textchecker.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ValidationException extends BusinessException {
    private final Object[] args;

    public ValidationException(@NonNull final String message, Object[] args) {
        super(message);
        this.args = args;
    }
}