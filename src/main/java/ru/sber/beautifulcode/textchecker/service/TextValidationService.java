package ru.sber.beautifulcode.textchecker.service;

import org.checkerframework.checker.nullness.qual.NonNull;
import ru.sber.beautifulcode.textchecker.model.Report;
import ru.sber.beautifulcode.textchecker.model.Text;

public interface TextValidationService {

    @NonNull
    Report validate(@NonNull final Text text);
}