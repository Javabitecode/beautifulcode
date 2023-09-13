package ru.sber.beautifulcode.textchecker.maper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sber.beautifulcode.textchecker.dto.DetailDto;
import ru.sber.beautifulcode.textchecker.dto.ErrorDto;
import ru.sber.beautifulcode.textchecker.exception.BusinessException;

@Mapper
public interface ErrorDtoMapper {
    @Mapping(target = "message", expression = "java(message)")
    ErrorDto toDto(String message, BusinessException ex);

    ErrorDto toDto(String message, List<DetailDto> details);
}