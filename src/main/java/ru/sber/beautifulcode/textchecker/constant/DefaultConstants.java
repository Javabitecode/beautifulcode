package ru.sber.beautifulcode.textchecker.constant;


import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.sber.beautifulcode.textchecker.enums.BracketEnum;
import ru.sber.beautifulcode.textchecker.validator.text.impl.Param;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DefaultConstants {
    public static final int MAX_NUMBER_OF_CHARACTERS_TO_BE_PROCESSED = 10000;
    public static final Set<Param> DEFAULT_PARAMS = Set.of(
        Param.builder()
            .name(BracketEnum.ROUND.name())
            .build()
    );
}