package ru.sber.beautifulcode.textchecker.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.lang.Nullable;

@Getter
@RequiredArgsConstructor
public enum ValidatorTypeEnum {
    BRACKET("Валидатор скобок");

    private static final Map<String, ValidatorTypeEnum> VALUES = Arrays.stream(values())
        .collect(Collectors.toMap(Enum::name, Function.identity()));

    private final String description;

    @Nullable
    public static ValidatorTypeEnum getEnum(@NonNull String name) {
        return VALUES.get(name);
    }
}