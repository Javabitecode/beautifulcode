package ru.sber.beautifulcode.textchecker.exception;

import java.time.OffsetDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {
    private final String code;
    private final OffsetDateTime createDate;

    public BusinessException(String code) {
        super(code);
        this.code = code;
        this.createDate = OffsetDateTime.now();
    }
}