package ru.sber.beautifulcode.textchecker.validator;

import java.util.Set;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface ValidationExecutor<V> {
    /**
     * Метод вызывает проверку объекта
     *
     * @param value - проверяемый объект
     * @return результат проверки {@link ConstraintViolation}
     */
    @NonNull
    Set<ConstraintViolation> execute(@NonNull V value);
}