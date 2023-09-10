package ru.sber.beautifulcode.textchecker.service;

import org.checkerframework.checker.nullness.qual.NonNull;
import ru.sber.beautifulcode.textchecker.model.TextData;
import ru.sber.beautifulcode.textchecker.model.Report;

public interface DatasetValidationService {

    @NonNull
    Report validate(@NonNull final TextData textData);
}