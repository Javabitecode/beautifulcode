package ru.sber.beautifulcode.textchecker.dto;

import static ru.sber.beautifulcode.textchecker.constant.DefaultConstants.MAX_NUMBER_OF_CHARACTERS_TO_BE_PROCESSED;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Schema(title = "Текстовые данные")
@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TextDataRq {

    @Schema(
        title = "Текст",
        example = "С реки повеяло ночной прохладой (солнце еще не взошло) и какой-то промозглой сыростью и запахом тины.",
        maxLength = MAX_NUMBER_OF_CHARACTERS_TO_BE_PROCESSED
    )
    @Max(value = MAX_NUMBER_OF_CHARACTERS_TO_BE_PROCESSED, message = "{api.error.text_data.text.max}")
    @NotBlank(message = "{api.error.text_data.text.not_blank}")
    private String text;
}