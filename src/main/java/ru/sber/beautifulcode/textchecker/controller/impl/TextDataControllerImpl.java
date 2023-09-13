package ru.sber.beautifulcode.textchecker.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.beautifulcode.textchecker.controller.TextDataController;
import ru.sber.beautifulcode.textchecker.dto.ReportRs;
import ru.sber.beautifulcode.textchecker.dto.TextDataRq;
import ru.sber.beautifulcode.textchecker.maper.ReportMapper;
import ru.sber.beautifulcode.textchecker.maper.TextDataMapper;
import ru.sber.beautifulcode.textchecker.service.TextDataValidationService;


@RestController
@RequiredArgsConstructor
public class TextDataControllerImpl implements TextDataController {
    private final TextDataMapper textDataMapper;
    private final ReportMapper reportMapper;
    private final TextDataValidationService textDataValidationService;

    @Override
    public ReportRs checkBrackets(TextDataRq rq) {
        var textData = textDataMapper.toModel(rq);
        var report = textDataValidationService.validate(textData);
        return reportMapper.toDto(report);
    }
}