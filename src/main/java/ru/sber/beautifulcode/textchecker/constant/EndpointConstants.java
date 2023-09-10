package ru.sber.beautifulcode.textchecker.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EndpointConstants {
    public static final String V1 = "/v1";
    public static final String API = "/api";
    public static final String CHECK_BRACKETS = "/checkBrackets";
    public static final String CHECK_BRACKETS_PATH = API + V1 + CHECK_BRACKETS;
}