package ru.sber.beautifulcode.textchecker.model;

import static ru.sber.beautifulcode.textchecker.constant.DefaultConstants.PARAMS;

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
    Set<Param> params = PARAMS;
}