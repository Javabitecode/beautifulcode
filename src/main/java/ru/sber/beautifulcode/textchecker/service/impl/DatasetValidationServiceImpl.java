package ru.sber.beautifulcode.textchecker.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.stereotype.Service;
import ru.sber.beautifulcode.textchecker.maper.ConstraintViolationMapper;
import ru.sber.beautifulcode.textchecker.maper.ValidationTextMapper;
import ru.sber.beautifulcode.textchecker.model.TextData;
import ru.sber.beautifulcode.textchecker.model.Report;
import ru.sber.beautifulcode.textchecker.service.DatasetValidationService;
import ru.sber.beautifulcode.textchecker.validator.text.impl.TextValidationExecutor;
import ru.sber.beautifulcode.textchecker.validator.text.impl.ValidationText;

@Slf4j
@Service
@AllArgsConstructor
public class DatasetValidationServiceImpl implements DatasetValidationService {
    private final TextValidationExecutor textValidationExecutor;
    private final ValidationTextMapper validationTextMapper;
    private final ConstraintViolationMapper constraintViolationMapper;

    @NonNull
    @Override
    public Report validate(@NonNull final TextData textData) {
        ValidationText validationText = validationTextMapper.convert(textData);
        var result = textValidationExecutor.execute(validationText);
        return constraintViolationMapper.convert(result);
    }
}