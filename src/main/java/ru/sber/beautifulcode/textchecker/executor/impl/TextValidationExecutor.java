package ru.sber.beautifulcode.textchecker.executor.impl;


import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.stereotype.Component;
import ru.sber.beautifulcode.textchecker.enums.ValidatorTypeEnum;
import ru.sber.beautifulcode.textchecker.executor.ValidationExecutor;
import ru.sber.beautifulcode.textchecker.factory.TextValidatorFactory;
import ru.sber.beautifulcode.textchecker.model.ConstraintViolation;
import ru.sber.beautifulcode.textchecker.model.Param;
import ru.sber.beautifulcode.textchecker.model.ValidationText;

@Component
@RequiredArgsConstructor
public class TextValidationExecutor implements ValidationExecutor<ValidationText> {
    private final TextValidatorFactory textValidatorFactory;

    @NonNull
    @Override
    public Set<ConstraintViolation> execute(@NonNull final ValidationText validationText) {
        return validationText.getParams().stream()
            .map(validate(validationText))
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());
    }

    @NonNull
    private Function<Param, ConstraintViolation> validate(@NonNull final ValidationText validationText) {
        return param -> {
            var validatorType = ValidatorTypeEnum.getEnum(param.getName());
            var validator = textValidatorFactory.get(validatorType);
            return validator != null
                ? validator.validate(validationText.getText(), param)
                : null;
        };
    }
}