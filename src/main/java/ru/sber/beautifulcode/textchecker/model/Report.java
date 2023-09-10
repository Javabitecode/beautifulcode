package ru.sber.beautifulcode.textchecker.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Report {
    private boolean correct;
}