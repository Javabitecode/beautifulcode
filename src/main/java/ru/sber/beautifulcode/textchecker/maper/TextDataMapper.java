package ru.sber.beautifulcode.textchecker.maper;

import org.mapstruct.Mapper;
import ru.sber.beautifulcode.textchecker.dto.TextDataRq;
import ru.sber.beautifulcode.textchecker.model.TextData;

@Mapper
public interface TextDataMapper {

    TextData toModel(TextDataRq rq);
}