package ru.sber.beautifulcode.textchecker;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.sber.beautifulcode.textchecker.config.MessageSourceConfig;

@SpringBootTest(
    classes = {
        TextCheckerApplication.class,
        MessageSourceConfig.class
    },
    webEnvironment = MOCK
)
public abstract class IntegrationTest {
    protected MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext webApplicationContext;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected MessageSourceAccessor messageSourceAccessor;

    @SneakyThrows
    protected String toString(Object object) {
        return objectMapper.writeValueAsString(object);
    }

    @BeforeEach
    public void setup() {
        if (this.mockMvc == null) {
            initMvc();
        }
    }

    protected void initMvc() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .alwaysDo(MockMvcResultHandlers.print())
            .build();
    }
}