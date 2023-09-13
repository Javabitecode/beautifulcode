package ru.sber.beautifulcode.textchecker.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.sber.beautifulcode.textchecker.constant.EndpointConstants.CHECK_BRACKETS_PATH;

import lombok.SneakyThrows;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.sber.beautifulcode.textchecker.IntegrationTest;
import ru.sber.beautifulcode.textchecker.dto.TextDataRq;
import ru.sber.beautifulcode.textchecker.service.TextDataValidationService;

class TextDataControllerTest extends IntegrationTest {
    @SpyBean
    private TextDataValidationService textDataValidationService;

    @DisplayName("Должен прийти успешный результат проверки")
    @SneakyThrows
    @MethodSource("ru.sber.beautifulcode.textchecker.source.TextDataSource#getSuccessTextDataRqs")
    @ParameterizedTest
    void shouldComeBackSuccessfulTextCheckResult(TextDataRq rq) {
        // when
        var actualRs = checkBracketsRequest(rq);

        // then
        actualRs
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", Matchers.notNullValue()))
            .andExpect(jsonPath("correct", Matchers.is(true)));

        verify(textDataValidationService).validate(any());
    }

    @DisplayName("Должен прийти не успешный результат проверки")
    @SneakyThrows
    @MethodSource("ru.sber.beautifulcode.textchecker.source.TextDataSource#getUnsuccessfulTextDataRqs")
    @ParameterizedTest
    void shouldComeBackUnsuccessfulTextCheckResult(TextDataRq rq) {
        // when
        var actualRs = checkBracketsRequest(rq);

        // then
        actualRs
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", Matchers.notNullValue()))
            .andExpect(jsonPath("correct", Matchers.is(false)));

        verify(textDataValidationService).validate(any());
    }

    @NonNull
    @SneakyThrows
    private ResultActions checkBracketsRequest(TextDataRq rq) {
        return mockMvc
            .perform(
                post(CHECK_BRACKETS_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toString(rq))
            )
            .andDo(print());
    }
}