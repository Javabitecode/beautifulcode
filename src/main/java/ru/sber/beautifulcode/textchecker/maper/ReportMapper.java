package ru.sber.beautifulcode.textchecker.maper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sber.beautifulcode.textchecker.dto.ReportRs;
import ru.sber.beautifulcode.textchecker.model.Report;

@Mapper
public interface ReportMapper {

    @Mapping(target = "isCorrect", source = "correct")
    ReportRs toDto(Report report);
}