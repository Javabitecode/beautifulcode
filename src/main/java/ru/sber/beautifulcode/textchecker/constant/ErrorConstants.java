package ru.sber.beautifulcode.textchecker.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorConstants {
    public static final String VALIDATION_CODE = "api.error.validation";
    public static final String TEXT_DATA_TEXT_SIZE_CODE = "{api.error.text_data.text.size}";
    public static final String TEXT_DATA_TEXT_NOT_BLANK_CODE = "{api.error.text_data.text.not_blank}";
    public static final String CHECKER_NOT_FOUND_PAIR = "error.pair.checker.not_found_pair";
    public static final String CHECKER_FOUND_BLANK_SEQUENCE = "error.pair.checker.found.blank_sequence";
    public static final String VALIDATOR_TYPE_NOT_VALID = "error.validator.type.not_valid";
}