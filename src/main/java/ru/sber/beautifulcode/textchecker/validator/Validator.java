package ru.sber.beautifulcode.textchecker.validator;

import org.checkerframework.checker.nullness.qual.NonNull;
import ru.sber.beautifulcode.textchecker.enums.ValidatorTypeEnum;

public interface Validator<V, P> {

    /**
     * Метод проверки объекта по параметрам
     *
     * @param value  - проверяемое значение
     * @param params - параметры по которым происходит проверка
     * @return результат проверки
     */
    @NonNull
    ConstraintViolation validate(@NonNull V value, @NonNull P params);

    /**
     * Метод возвращает тип валидатора
     *
     * @return тип валидатора {@link ValidatorTypeEnum}
     */
    @NonNull
    ValidatorTypeEnum getType();
}