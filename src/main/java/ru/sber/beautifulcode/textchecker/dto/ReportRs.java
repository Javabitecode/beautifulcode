package ru.sber.beautifulcode.textchecker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(title = "Отчет о проверке данных")
@Data
@Builder
public class ReportRs {

    @Schema(
        title = "Флаг, отображающий корректность переданных данных",
        example = "true"
    )
    private boolean isCorrect;
}