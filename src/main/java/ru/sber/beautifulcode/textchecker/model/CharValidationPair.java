package ru.sber.beautifulcode.textchecker.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CharValidationPair {
    private Character left;
    private Character right;
    private Integer leftIndex;
    private Integer rightIndex;
    private String message;
}