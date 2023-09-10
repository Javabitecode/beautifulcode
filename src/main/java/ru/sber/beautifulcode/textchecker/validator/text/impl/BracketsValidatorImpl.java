package ru.sber.beautifulcode.textchecker.validator.text.impl;


import static ru.sber.beautifulcode.textchecker.enums.ValidatorTypeEnum.BRACKET;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.stereotype.Component;
import ru.sber.beautifulcode.textchecker.enums.BracketEnum;
import ru.sber.beautifulcode.textchecker.enums.ValidatorTypeEnum;
import ru.sber.beautifulcode.textchecker.exception.ValidationException;
import ru.sber.beautifulcode.textchecker.utils.CharacterPairCheckerUtil;
import ru.sber.beautifulcode.textchecker.validator.ConstraintViolation;
import ru.sber.beautifulcode.textchecker.validator.text.TextValidator;

@Component
public class BracketsValidatorImpl implements TextValidator {

    @Override
    public ConstraintViolation validate(@NonNull final String text, @NonNull final Param param) {
        checkParam(param);
        var brackets = BracketEnum.getAllByNames(param.getValues());
        var pairs = BracketEnum.getPairs(brackets);
        var result = CharacterPairCheckerUtil.check(text, pairs);
        return ConstraintViolationImpl.builder()
            .valid(result)
            .build();
    }

    @Override
    public ValidatorTypeEnum getType() {
        return BRACKET;
    }

    private void checkParam(@NonNull final Param param) {
        if (isNotValidType(param)) {
            throw new ValidationException(
                //TODO переработать ошибки
                String.format(
                    "В параметрах задан не корректный тип обработчика текста. Ожидался: %s, Пришел: %s",
                    getType().getDescription(),
                    param.getName()
                )
            );
        }
    }

    private boolean isNotValidType(@NonNull final Param param) {
        return !getType().name().equals(param.getName());
    }
}