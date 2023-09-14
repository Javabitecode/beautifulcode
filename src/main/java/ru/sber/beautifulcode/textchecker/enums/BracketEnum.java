package ru.sber.beautifulcode.textchecker.enums;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
@RequiredArgsConstructor
public enum BracketEnum {
    ROUND('(', ')');
    private static final Map<String, BracketEnum> VALUES = Arrays.stream(values())
        .collect(Collectors.toMap(Enum::name, Function.identity()));

    private final char left;
    private final char right;

    @NonNull
    public static BiMap<Character, Character> getPairs(@NonNull final List<BracketEnum> brackets) {
        return HashBiMap.create(
            brackets.stream()
                .collect(Collectors.toMap(BracketEnum::getLeft, BracketEnum::getRight))
        );
    }

    @NonNull
    public static List<BracketEnum> getAllByNames(@NonNull final List<String> names) {
        return names.stream()
            .map(VALUES::get)
            .filter(Objects::nonNull)
            .toList();
    }
}