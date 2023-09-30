package ru.sber.beautifulcode.textchecker.maper;

import org.mapstruct.Mapper;
import ru.sber.beautifulcode.textchecker.dto.TextRq;
import ru.sber.beautifulcode.textchecker.model.Text;

@Mapper
public interface TextMapper {

    Text toModel(TextRq rq);
}