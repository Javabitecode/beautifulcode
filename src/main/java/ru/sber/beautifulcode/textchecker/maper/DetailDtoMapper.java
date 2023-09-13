package ru.sber.beautifulcode.textchecker.maper;

import java.util.ArrayList;
import java.util.List;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.mapstruct.Mapper;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.sber.beautifulcode.textchecker.dto.DetailDto;

@Mapper
public interface DetailDtoMapper {

    private static List<DetailDto> getDetails(@NonNull final List<FieldError> fieldErrors) {
        var details = new ArrayList<DetailDto>();
        fieldErrors.forEach(fieldError -> {
            var field = fieldError.getField();
            var message = fieldError.getDefaultMessage();
            DetailDto detail = getDetailDto(field, message);
            details.add(detail);
        });
        return details;
    }

    private static DetailDto getDetailDto(@NonNull final String field, @Nullable final String message) {
        return DetailDto.builder()
            .field(field)
            .message(message)
            .build();
    }

    default List<DetailDto> toDtos(@NonNull final MethodArgumentNotValidException ex) {
        var fieldErrors = ex.getBindingResult().getFieldErrors();
        return getDetails(fieldErrors);
    }
}