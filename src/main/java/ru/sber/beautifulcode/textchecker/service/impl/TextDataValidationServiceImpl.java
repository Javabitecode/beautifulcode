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
import ru.sber.beautifulcode.textchecker.model.TextData;
import ru.sber.beautifulcode.textchecker.model.ValidationText;
import ru.sber.beautifulcode.textchecker.service.TextDataValidationService;

@Slf4j
@Service
@AllArgsConstructor
public class TextDataValidationServiceImpl implements TextDataValidationService {
    private final TextValidationExecutor textValidationExecutor;
    private final ConstraintViolationMapper constraintViolationMapper;
    private final ValidationTextMapper validationTextMapper;


    @NonNull
    @Override
    public Report validate(@NonNull final TextData textData) {
        ValidationText validationText = validationTextMapper.convert(textData);
        var result = textValidationExecutor.execute(validationText);
        logging(result);
        return constraintViolationMapper.convert(result);
    }

    private void logging(@NonNull final Set<ConstraintViolation> result) {
        result.stream()
            .filter(res -> !res.isValid())
            .forEach(res -> log.warn("Ошибки валидации: {}", res));
    }
}