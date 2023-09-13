package ru.sber.beautifulcode.textchecker.validator.text.impl;


import static ru.sber.beautifulcode.textchecker.constant.ErrorConstants.VALIDATOR_TYPE_NOT_VALID;
import static ru.sber.beautifulcode.textchecker.enums.ValidatorTypeEnum.BRACKET;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Component;
import ru.sber.beautifulcode.textchecker.checker.impl.CharacterPairChecker;
import ru.sber.beautifulcode.textchecker.enums.BracketEnum;
import ru.sber.beautifulcode.textchecker.enums.ValidatorTypeEnum;
import ru.sber.beautifulcode.textchecker.exception.ValidationException;
import ru.sber.beautifulcode.textchecker.model.CharValidationPair;
import ru.sber.beautifulcode.textchecker.model.ConstraintViolation;
import ru.sber.beautifulcode.textchecker.model.Param;
import ru.sber.beautifulcode.textchecker.model.impl.ConstraintViolationImpl;
import ru.sber.beautifulcode.textchecker.validator.text.TextValidator;

@Slf4j
@Component
@RequiredArgsConstructor
public class BracketsValidatorImpl implements TextValidator {
    private final CharacterPairChecker characterPairChecker;

    @NonNull
    @Override
    public ConstraintViolation validate(@NonNull final String text, @NonNull final Param param) {
        checkParam(param);
        var brackets = BracketEnum.getAllByNames(param.getValues());
        var pairs = BracketEnum.getPairs(brackets);
        var result = characterPairChecker.check(text, pairs);
        var message = collectMessage(result);
        return getConstraintViolation(result, message);
    }

    @NonNull
    @Override
    public ValidatorTypeEnum getType() {
        return BRACKET;
    }

    private void checkParam(@NonNull final Param param) {
        if (isNotValidType(param)) {
            throw new ValidationException(
                VALIDATOR_TYPE_NOT_VALID,
                new Object[]{getType().getDescription(), param.getName()}
            );
        }
    }

    private boolean isNotValidType(@NonNull final Param param) {
        return !getType().name().equals(param.getName());
    }

    @NonNull
    private String collectMessage(@NonNull final List<CharValidationPair> result) {
        return result.stream()
            .map(CharValidationPair::getMessage)
            .filter(Objects::nonNull)
            .collect(Collectors.joining(", "));
    }

    @NonNull
    private ConstraintViolationImpl getConstraintViolation(@NonNull final List<CharValidationPair> result,
                                                           @Nullable final String message) {
        return ConstraintViolationImpl.builder()
            .valid(result.isEmpty())
            .message(message)
            .build();
    }
}