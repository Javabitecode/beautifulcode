package ru.sber.beautifulcode.textchecker.model;

public interface ConstraintViolation {

    /**
     * Метод возвращает статус валидности объекта
     *
     * @return статус валидности
     */
    boolean isValid();

    /**
     * Метод возвращает сообщение об ощибке
     *
     * @return сообщение об ощибке
     */
    String getMessage();
}