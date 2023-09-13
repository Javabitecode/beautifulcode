package ru.sber.beautifulcode.textchecker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(title = "Ошибка")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorDto {

    @Schema(
        title = "Дата создания оишбки",
        example = "2023-09-10T12:59:50.240853600+03:00"
    )
    @Builder.Default
    private OffsetDateTime createDate = OffsetDateTime.now();

    @Schema(
        title = "Сообщение",
        example = "Ошибка валидации данных"
    )
    private String message;

    @Schema(
        title = "Детали ошибки",
        example = """
            [
                {
                    "field": "text",
                    "message": "Размер текста должен находиться в диапазоне от 0 до 10000 символов"
                },
                {
                    "field": "text",
                    "message": "Текст не может быть пустым"
                }
            ]"""
    )
    private List<DetailDto> details;
}