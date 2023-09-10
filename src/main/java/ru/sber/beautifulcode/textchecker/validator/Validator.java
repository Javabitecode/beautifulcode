package ru.sber.beautifulcode.textchecker.validator;

import org.checkerframework.checker.nullness.qual.NonNull;
import ru.sber.beautifulcode.textchecker.enums.ValidatorTypeEnum;

public interface Validator<V, P> {

    ConstraintViolation validate(@NonNull V v, @NonNull P p);

    ValidatorTypeEnum getType();
}