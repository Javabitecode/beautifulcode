package ru.sber.beautifulcode.textchecker.validator.text.impl;

import static ru.sber.beautifulcode.textchecker.constant.DefaultConstants.DEFAULT_PARAMS;

import java.util.Set;
import lombok.Builder;
import lombok.Data;
import org.checkerframework.checker.nullness.qual.NonNull;

@Data
@Builder(toBuilder = true)
public class ValidationText {

    @NonNull
    String text;

    @NonNull
    @Builder.Default
    Set<Param> params = DEFAULT_PARAMS;
}