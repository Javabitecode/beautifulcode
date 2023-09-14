package ru.sber.beautifulcode.textchecker.checker.impl;

import static ru.sber.beautifulcode.textchecker.constant.ErrorConstants.CHECKER_FOUND_BLANK_SEQUENCE;
import static ru.sber.beautifulcode.textchecker.constant.ErrorConstants.CHECKER_NOT_FOUND_PAIR;

import com.google.common.collect.BiMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import ru.sber.beautifulcode.textchecker.checker.Checker;
import ru.sber.beautifulcode.textchecker.model.CharValidationPair;

@Component
@RequiredArgsConstructor
public class CharacterPairChecker implements Checker<List<CharValidationPair>, String, BiMap<Character, Character>> {
    private final MessageSourceAccessor messageSourceAccessor;

    @NonNull
    @Override
    public List<CharValidationPair> check(@NonNull final String text,
                                          @NonNull final BiMap<Character, Character> pairs) {
        List<CharValidationPair> withPairs = new ArrayList<>();
        Deque<CharValidationPair> withoutPairs = new LinkedList<>();
        var chars = text.toCharArray();
        for (int step = 0; step < chars.length; step++) {
            var element = chars[step];
            if (isFoundLeft(pairs, element)) {
                withoutPairs.push(getCharPairWithLeft(element, step));
            } else if (isFoundRight(pairs, element)) {
                var left = withoutPairs.peek();
                if (left != null && element == pairs.get(left.getLeft())) {
                    withoutPairs.removeFirst();
                    var charPair = getCharPair(element, step, left.getLeft(), left.getLeftIndex());
                    withPairs.add(charPair);
                } else {
                    withoutPairs.push(getCharPairWithRight(element, step));
                }
            }
        }
        return getCharValidationPairsWithMessage(withPairs, withoutPairs, chars);
    }

    @NonNull
    private List<CharValidationPair> getCharValidationPairsWithMessage(@NonNull final List<CharValidationPair> withPairs,
                                                                       @NonNull final Deque<CharValidationPair> withoutPairs,
                                                                       final char[] chars) {
        var allBlanksWithMessage = getAllBlanksWithMessage(withPairs, chars);
        var allWithoutPairWithMessage = getAllWithoutPairWithMessage(withoutPairs);
        var pairsWithMessage = new ArrayList<CharValidationPair>();
        pairsWithMessage.addAll(allBlanksWithMessage);
        pairsWithMessage.addAll(allWithoutPairWithMessage);
        return pairsWithMessage;
    }

    private boolean isFoundLeft(@NonNull final BiMap<Character, Character> pairs,
                                final char element) {
        return pairs.containsKey(element);
    }

    private boolean isFoundRight(@NonNull final BiMap<Character, Character> pairs,
                                 final char element) {
        return pairs.inverse().containsKey(element);
    }

    @NonNull
    private CharValidationPair getCharPair(@Nullable final Character right,
                                           @Nullable final Integer rightIndex,
                                           @Nullable final Character left,
                                           @Nullable final Integer leftIndex) {
        return CharValidationPair.builder()
            .right(right)
            .rightIndex(rightIndex)
            .left(left)
            .leftIndex(leftIndex)
            .build();
    }

    @NonNull
    private CharValidationPair getCharPairWithRight(final char right, final int rightIndex) {
        return getCharPair(right, rightIndex, null, null);
    }

    @NonNull
    private CharValidationPair getCharPairWithLeft(final char left, final int leftIndex) {
        return getCharPair(null, null, left, leftIndex);
    }

    @NonNull
    private Collection<CharValidationPair> getAllWithoutPairWithMessage(@NonNull final Collection<CharValidationPair> charValidationPairs) {
        return charValidationPairs.stream()
            .map(charValidationPair -> getWithMessage(charValidationPair, CHECKER_NOT_FOUND_PAIR, new Object[]{charValidationPair}))
            .toList();
    }

    @NonNull
    private List<CharValidationPair> getAllBlanksWithMessage(@NonNull final List<CharValidationPair> pairsForValidate, final char[] chars) {
        return pairsForValidate.stream()
            .filter(charValidationPair -> isBlank(chars, charValidationPair))
            .map(charValidationPair -> getWithMessage(charValidationPair, CHECKER_FOUND_BLANK_SEQUENCE, new Object[]{charValidationPair}))
            .toList();
    }

    private boolean isBlank(char[] chars, CharValidationPair charValidationPair) {
        var from = Math.incrementExact(charValidationPair.getLeftIndex());
        var to = charValidationPair.getRightIndex();
        return isBlank(chars, from, to);
    }

    private boolean isBlank(final char[] chars, final int from, final int to) {
        for (int i = from; i < to; i++) {
            var element = chars[i];
            if (!Character.isSpaceChar(element)) {
                return false;
            }
        }
        return true;
    }

    @NonNull
    private CharValidationPair getWithMessage(@NonNull final CharValidationPair charValidationPair,
                                              @NonNull final String code,
                                              @Nullable final Object[] args) {
        return charValidationPair.toBuilder()
            .message(messageSourceAccessor.getMessage(code, args))
            .build();
    }
}