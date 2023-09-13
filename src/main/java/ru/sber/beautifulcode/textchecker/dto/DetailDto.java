package ru.sber.beautifulcode.textchecker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Schema(title = "Деталь ошибки")
@Data
@Builder
@AllArgsConstructor
public class DetailDto {

    @Schema(
        title = "Валидируемое поле",
        example = "text"
    )
    private String field;

    @Schema(
        title = "Сообщение",
        example = "Размер текста должен находиться в диапазоне от 0 до 10000 символов"
    )
    private String message;
}