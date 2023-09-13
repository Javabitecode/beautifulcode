package ru.sber.beautifulcode.textchecker.constant;


import static ru.sber.beautifulcode.textchecker.enums.BracketEnum.ROUND;
import static ru.sber.beautifulcode.textchecker.enums.BracketEnum.SQUARE;
import static ru.sber.beautifulcode.textchecker.enums.ValidatorTypeEnum.BRACKET;

import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.sber.beautifulcode.textchecker.validator.text.impl.Param;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DefaultConstants {
    public static final int MAX_NUMBER_OF_CHARACTERS_TO_BE_PROCESSED = 10000;
    public static final List<String> PARAM_VALUES = List.of(ROUND.name(), SQUARE.name());
    public static final Set<Param> PARAMS = Set.of(
        Param.builder()
            .name(BRACKET.name())
            .values(PARAM_VALUES)
            .build()
    );
}