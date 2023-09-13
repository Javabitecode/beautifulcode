package ru.sber.beautifulcode.textchecker.maper;

import org.mapstruct.Mapper;
import ru.sber.beautifulcode.textchecker.model.TextData;
import ru.sber.beautifulcode.textchecker.model.ValidationText;

@Mapper
public interface ValidationTextMapper {

    ValidationText convert(TextData textData);
}