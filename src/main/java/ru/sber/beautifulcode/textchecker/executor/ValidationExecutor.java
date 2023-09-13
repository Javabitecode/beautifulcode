package ru.sber.beautifulcode.textchecker.executor;

import java.util.Set;
import org.checkerframework.checker.nullness.qual.NonNull;
import ru.sber.beautifulcode.textchecker.model.ConstraintViolation;

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