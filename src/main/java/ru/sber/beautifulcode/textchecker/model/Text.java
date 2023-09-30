package ru.sber.beautifulcode.textchecker.model;


import lombok.Builder;
import lombok.Data;
import org.checkerframework.checker.nullness.qual.NonNull;

@Data
@Builder
public class Text {

    @NonNull
    private String text;
}