package ru.sber.beautifulcode.textchecker.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.sber.beautifulcode.textchecker.constant.EndpointConstants.CHECK_BRACKETS_PATH;
import static ru.sber.beautifulcode.textchecker.constant.ErrorConstants.TEXT_DATA_TEXT_NOT_BLANK_CODE;
import static ru.sber.beautifulcode.textchecker.constant.ErrorConstants.VALIDATION_CODE;

import lombok.SneakyThrows;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.sber.beautifulcode.textchecker.IntegrationTest;
import ru.sber.beautifulcode.textchecker.dto.TextRq;
import ru.sber.beautifulcode.textchecker.service.TextValidationService;
import ru.sber.beautifulcode.textchecker.source.TextSource;

class TextControllerTest extends IntegrationTest {
    private static final String TEXT_DATA_TEXT_NOT_BLANK_CODE_WITH_OUT_BRACKETS = TEXT_DATA_TEXT_NOT_BLANK_CODE.substring(
        1,
        TEXT_DATA_TEXT_NOT_BLANK_CODE.length() - 1
    );
    @SpyBean
    private TextValidationService textValidationService;

    @DisplayName("Должен прийти успешный результат проверки")
    @SneakyThrows
    @MethodSource("ru.sber.beautifulcode.textchecker.source.TextSource#getSuccessTextDataRqs")
    @ParameterizedTest
    void shouldComeBackSuccessfulTextCheckResult(TextRq rq) {
        // when
        var actualRs = checkBracketsRequest(rq);

        // then
        actualRs
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", Matchers.notNullValue()))
            .andExpect(jsonPath("correct", Matchers.is(true)));

        verify(textValidationService).validate(any());
    }

    @DisplayName("Должен прийти не успешный результат проверки")
    @SneakyThrows
    @MethodSource("ru.sber.beautifulcode.textchecker.source.TextSource#getUnsuccessfulTextDataRqs")
    @ParameterizedTest
    void shouldComeBackUnsuccessfulTextCheckResult(TextRq rq) {
        // when
        var actualRs = checkBracketsRequest(rq);

        // then
        actualRs
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", Matchers.notNullValue()))
            .andExpect(jsonPath("correct", Matchers.is(false)));

        verify(textValidationService).validate(any());
    }

    @DisplayName("Должна прийти ошибка валдиции пустого текста")
    @SneakyThrows
    @Test
    void should_come_error_in_validation_of_empty_text() {
        // when
        var actualRs = checkBracketsRequest(TextSource.RQ_BLANK);

        // then
        actualRs
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$", Matchers.notNullValue()))
            .andExpect(jsonPath("message", Matchers.is(messageSourceAccessor.getMessage(VALIDATION_CODE))))
            .andExpect(jsonPath("details[0].message", Matchers.is(messageSourceAccessor.getMessage(TEXT_DATA_TEXT_NOT_BLANK_CODE_WITH_OUT_BRACKETS))));
    }

    @DisplayName("Должна прийти ошибка валидации максимально длины текста")
    @SneakyThrows
    @Test
    void should_be_validation_error_of_maximum_length_of_text() {
        // when
        var actualRs = checkBracketsRequest(TextSource.RQ_TEXT_OVER_MAX_LENGTH);

        // then
        actualRs
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$", Matchers.notNullValue()))
            .andExpect(jsonPath("message", Matchers.is(messageSourceAccessor.getMessage(VALIDATION_CODE))));
    }

    @NonNull
    @SneakyThrows
    private ResultActions checkBracketsRequest(TextRq rq) {
        return mockMvc
            .perform(
                post(CHECK_BRACKETS_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toString(rq))
            )
            .andDo(print());
    }
}