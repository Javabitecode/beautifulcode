package ru.sber.beautifulcode.textchecker.factory.impl;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Component;
import ru.sber.beautifulcode.textchecker.enums.ValidatorTypeEnum;
import ru.sber.beautifulcode.textchecker.factory.TextValidatorFactory;
import ru.sber.beautifulcode.textchecker.validator.text.TextValidator;

@Data
@Component
public class TextValidatorFactoryImpl implements TextValidatorFactory {
    private Map<ValidatorTypeEnum, TextValidator> validators;

    public TextValidatorFactoryImpl(@NonNull final List<TextValidator> textValidators) {
        validators = new EnumMap<>(ValidatorTypeEnum.class);
        textValidators.forEach(validator -> validators.put(validator.getType(), validator));
    }

    @Nullable
    @Override
    public TextValidator get(@Nullable final ValidatorTypeEnum type) {
        return validators.get(type);
    }
}