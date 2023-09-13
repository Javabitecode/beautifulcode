package ru.sber.beautifulcode.textchecker.service;

import org.checkerframework.checker.nullness.qual.NonNull;
import ru.sber.beautifulcode.textchecker.model.Report;
import ru.sber.beautifulcode.textchecker.model.TextData;

public interface TextDataValidationService {

    @NonNull
    Report validate(@NonNull final TextData textData);
}