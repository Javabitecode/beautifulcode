package ru.sber.beautifulcode.textchecker.factory;

public interface SimpleFactory<I, V> {
    /**
     * Метод получения экземпляра объекта по значению
     *
     * @param value - значение
     * @return экземпляр объекта
     */
    I get(V value);
}