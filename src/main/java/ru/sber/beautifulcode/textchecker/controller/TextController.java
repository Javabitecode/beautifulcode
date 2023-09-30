package ru.sber.beautifulcode.textchecker.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.sber.beautifulcode.textchecker.constant.EndpointConstants.CHECK_BRACKETS_PATH;
import static ru.sber.beautifulcode.textchecker.constant.HttpConstants.OK;
import static ru.sber.beautifulcode.textchecker.constant.HttpConstants.SUCCESSFUL_OPERATION;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.sber.beautifulcode.textchecker.dto.ReportRs;
import ru.sber.beautifulcode.textchecker.dto.TextRq;

@Tag(name = "Проверка текстовых данных")
public interface TextController {

    /**
     * По ТЗ необходимо было использовать метод checkBrackets(...), url /checkBrackets и rq: String text.
     * На мой взгляд, это плохо спроектированное api
     */
    @Operation(summary = "Получить отчет о проверке текстовых данных")
    @ApiResponse(
        responseCode = OK,
        description = SUCCESSFUL_OPERATION,
        content = @Content(
            mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ReportRs.class)
        )
    )
    @PostMapping(CHECK_BRACKETS_PATH)
    ReportRs checkBrackets(@RequestBody @Valid TextRq rq);
}