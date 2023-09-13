package ru.sber.beautifulcode.textchecker.checker;

import org.checkerframework.checker.nullness.qual.NonNull;

public interface Checker<R, V, P> {
    /**
     * Метод проверки объекта по определенному параметру
     *
     * @param value - проверяемое значение
     * @param param - параметры по которым происходит проверка
     * @return результат проверки
     */
    @NonNull
    R check(@NonNull V value, @NonNull P param);
}