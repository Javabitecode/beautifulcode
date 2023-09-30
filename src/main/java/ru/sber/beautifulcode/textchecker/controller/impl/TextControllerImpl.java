package ru.sber.beautifulcode.textchecker.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.beautifulcode.textchecker.controller.TextController;
import ru.sber.beautifulcode.textchecker.dto.ReportRs;
import ru.sber.beautifulcode.textchecker.dto.TextRq;
import ru.sber.beautifulcode.textchecker.maper.ReportMapper;
import ru.sber.beautifulcode.textchecker.maper.TextMapper;
import ru.sber.beautifulcode.textchecker.service.TextValidationService;


@RestController
@RequiredArgsConstructor
public class TextControllerImpl implements TextController {
    private final TextMapper textMapper;
    private final ReportMapper reportMapper;
    private final TextValidationService textValidationService;

    @Override
    public ReportRs checkBrackets(TextRq rq) {
        var text = textMapper.toModel(rq);
        var report = textValidationService.validate(text);
        return reportMapper.toDto(report);
    }
}