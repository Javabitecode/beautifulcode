package ru.sber.beautifulcode.textchecker.service.impl;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.stereotype.Service;
import ru.sber.beautifulcode.textchecker.executor.impl.TextValidationExecutor;
import ru.sber.beautifulcode.textchecker.maper.ConstraintViolationMapper;
import ru.sber.beautifulcode.textchecker.maper.ValidationTextMapper;
import ru.sber.beautifulcode.textchecker.model.ConstraintViolation;
import ru.sber.beautifulcode.textchecker.model.Report;
import ru.sber.beautifulcode.textchecker.model.Text;
import ru.sber.beautifulcode.textchecker.service.TextValidationService;

@Slf4j
@Service
@AllArgsConstructor
public class TextValidationServiceImpl implements TextValidationService {
    private final TextValidationExecutor textValidationExecutor;
    private final ConstraintViolationMapper constraintViolationMapper;
    private final ValidationTextMapper validationTextMapper;


    @NonNull
    @Override
    public Report validate(@NonNull final Text text) {
        var validationText = validationTextMapper.convert(text);
        var constraintViolations = textValidationExecutor.execute(validationText);
        logging(constraintViolations);
        return constraintViolationMapper.convert(constraintViolations);
    }

    private void logging(@NonNull final Set<ConstraintViolation> constraintViolations) {
        constraintViolations.stream()
            .filter(violation -> !violation.isValid())
            .forEach(violation -> log.warn("Ошибки валидации: {}", violation));
    }
}