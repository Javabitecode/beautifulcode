package ru.sber.beautifulcode.textchecker.factory;

import org.checkerframework.checker.nullness.qual.Nullable;
import ru.sber.beautifulcode.textchecker.enums.ValidatorTypeEnum;
import ru.sber.beautifulcode.textchecker.validator.text.TextValidator;

public interface TextValidatorFactory extends SimpleFactory<TextValidator, ValidatorTypeEnum> {

    @Nullable
    TextValidator get(@Nullable final ValidatorTypeEnum type);
}
