package ru.sber.beautifulcode.textchecker.exception.handler;

import static ru.sber.beautifulcode.textchecker.constant.ErrorConstants.VALIDATION_CODE;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.sber.beautifulcode.textchecker.dto.ErrorDto;
import ru.sber.beautifulcode.textchecker.exception.BusinessException;
import ru.sber.beautifulcode.textchecker.exception.ValidationException;
import ru.sber.beautifulcode.textchecker.maper.DetailDtoMapper;
import ru.sber.beautifulcode.textchecker.maper.ErrorDtoMapper;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class BusinessExceptionHandler {
    private final MessageSourceAccessor messageSourceAccessor;
    private final ErrorDtoMapper errorDtoMapper;
    private final DetailDtoMapper detailDtoMapper;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public ErrorDto handleBaseBusinessException(@NonNull final BusinessException ex) {
        var message = messageSourceAccessor.getMessage(ex.getCode());
        logging(message, ex);
        return errorDtoMapper.toDto(message, ex);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorDto handleValidationException(@NonNull final ValidationException ex) {
        var message = messageSourceAccessor.getMessage(ex.getCode(), ex.getArgs());
        logging(message, ex);
        return errorDtoMapper.toDto(message, ex);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDto handleUserMethodFieldErrors(@NonNull final MethodArgumentNotValidException ex) {
        var message = messageSourceAccessor.getMessage(VALIDATION_CODE);
        logging(message, ex);
        var details = detailDtoMapper.toDtos(ex);
        return errorDtoMapper.toDto(message, details);
    }

    private void logging(@NonNull final String message, @NonNull final Exception ex) {
        log.error("Error message: {} ", message, ex);
    }
}