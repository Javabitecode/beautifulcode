package ru.sber.beautifulcode.textchecker.source;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.sber.beautifulcode.textchecker.dto.TextDataRq;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TextDataSource {
    private static final String TEXT_SUCCESS_1 = "С реки повеяло ночной прохладой (солнце еще не взошло) и какой-то " +
        "промозглой сыростью и запахом тины.";
    private static final String TEXT_SUCCESS_2 = "После некоторых терминов в круглых скобках даются ссылки в виде цифр " +
        "(номер комментария) на комментарий (например: (23)).";
    private static final String TEXT_SUCCESS_3 = "(5+6) * 2; F (Х) = 4Х + 6.";
    private static final String TEXT_SUCCESS_4 = "Вчера я отправился в поход в лес (это мое любимое место для отдыха) " +
        "вместе с друзьями. Мы выбрали маршрут, который проходил через горные потоки и поля (для разнообразия). " +
        "В начале пути погода была отличной, солнце светило ярко, и птицы радостно пели. Однако, когда мы подошли ближе" +
        " к вершине горы, небо стало покрываться облаками, (как будто природа готовила нам небольшой сюрприз). Несмотря" +
        " на это, виды были захватывающими, особенно когда мы достигли высшей точки и увидели прекрасный вид на долину" +
        " (я почувствовал, что все усилия стоили того).";
    private static final String TEXT_SUCCESS_5 = "(example)( 1 ( 2(3(4 )5) 6 ) 7    )";
    private static final String TEXT_SUCCESS_6 = "(.)";
    private static final String TEXT_SUCCESS_7 = "(                                     example                      )";

    private static final String TEXT_UNSUCCESSFUL_1 = "()";
    private static final String TEXT_UNSUCCESSFUL_2 = "(example))";
    private static final String TEXT_UNSUCCESSFUL_3 = "(5+6) * 2)";
    private static final String TEXT_UNSUCCESSFUL_4 = "(я почувствовал, что все усилия стоили того))";
    private static final String TEXT_UNSUCCESSFUL_5 = "((я почувствовал, что все усилия стоили того)";
    private static final String TEXT_UNSUCCESSFUL_6 = "(                                                             )";
    private static final String TEXT_UNSUCCESSFUL_7 = "(example)example)";

    public static List<TextDataRq> getSuccessTextDataRqs() {
        return List.of(
            getTextDataRq(TEXT_SUCCESS_1),
            getTextDataRq(TEXT_SUCCESS_2),
            getTextDataRq(TEXT_SUCCESS_3),
            getTextDataRq(TEXT_SUCCESS_4),
            getTextDataRq(TEXT_SUCCESS_5),
            getTextDataRq(TEXT_SUCCESS_6),
            getTextDataRq(TEXT_SUCCESS_7)
        );
    }

    public static List<TextDataRq> getUnsuccessfulTextDataRqs() {
        return List.of(
            getTextDataRq(TEXT_UNSUCCESSFUL_1),
            getTextDataRq(TEXT_UNSUCCESSFUL_2),
            getTextDataRq(TEXT_UNSUCCESSFUL_3),
            getTextDataRq(TEXT_UNSUCCESSFUL_4),
            getTextDataRq(TEXT_UNSUCCESSFUL_5),
            getTextDataRq(TEXT_UNSUCCESSFUL_6),
            getTextDataRq(TEXT_UNSUCCESSFUL_7)
        );
    }

    private static TextDataRq getTextDataRq(@NonNull final String text) {
        return TextDataRq.builder()
            .text(text)
            .build();
    }
}