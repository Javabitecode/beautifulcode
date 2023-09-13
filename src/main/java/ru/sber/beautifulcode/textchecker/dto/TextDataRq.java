package ru.sber.beautifulcode.textchecker.dto;

import static ru.sber.beautifulcode.textchecker.constant.DefaultConstants.MAX_NUMBER_OF_CHARACTERS_TO_BE_PROCESSED;
import static ru.sber.beautifulcode.textchecker.constant.ErrorConstants.TEXT_DATA_TEXT_NOT_BLANK_CODE;
import static ru.sber.beautifulcode.textchecker.constant.ErrorConstants.TEXT_DATA_TEXT_SIZE_CODE;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(title = "Текстовые данные")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TextDataRq {

    @Schema(
        title = "Текст",
        example = "С реки повеяло ночной прохладой (солнце еще не взошло) и какой-то промозглой сыростью и запахом тины.",
        maxLength = MAX_NUMBER_OF_CHARACTERS_TO_BE_PROCESSED
    )
    @Size(max = MAX_NUMBER_OF_CHARACTERS_TO_BE_PROCESSED, message = TEXT_DATA_TEXT_SIZE_CODE)
    @NotBlank(message = TEXT_DATA_TEXT_NOT_BLANK_CODE)
    private String text;
}