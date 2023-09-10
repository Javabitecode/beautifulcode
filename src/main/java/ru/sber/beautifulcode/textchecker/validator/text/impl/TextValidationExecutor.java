package ru.sber.beautifulcode.textchecker.validator.text.impl;


import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.stereotype.Component;
import ru.sber.beautifulcode.textchecker.enums.ValidatorTypeEnum;
import ru.sber.beautifulcode.textchecker.factory.TextValidatorFactory;
import ru.sber.beautifulcode.textchecker.validator.ConstraintViolation;
import ru.sber.beautifulcode.textchecker.validator.ValidationExecutor;

@Component
@RequiredArgsConstructor
public class TextValidationExecutor implements ValidationExecutor<ValidationText> {
    private final TextValidatorFactory textValidatorFactory;

    @Override
    public Set<ConstraintViolation> execute(ValidationText validationText) {
        return validationText.getParams().stream()
            .map(validate(validationText))
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());
    }

    @NonNull
    private Function<Param, ConstraintViolation> validate(ValidationText validationText) {
        return param -> {
            var type = ValidatorTypeEnum.getEnum(param.getName());
            var validator = textValidatorFactory.getValidator(type);
            return validator != null
                ? validator.validate(validationText.getText(), param)
                : null;
        };
    }
}