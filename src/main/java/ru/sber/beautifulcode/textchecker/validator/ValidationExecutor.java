package ru.sber.beautifulcode.textchecker.validator;

import java.util.Set;

public interface ValidationExecutor<V> {

    Set<ConstraintViolation> execute(V v);
}