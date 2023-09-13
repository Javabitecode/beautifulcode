package ru.sber.beautifulcode.textchecker.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sber.beautifulcode.textchecker.controller.TextDataController;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public GroupedOpenApi apiV1() {
        return GroupedOpenApi.builder()
            .group("v1")
            .packagesToScan(TextDataController.class.getPackageName())
            .build();
    }
}