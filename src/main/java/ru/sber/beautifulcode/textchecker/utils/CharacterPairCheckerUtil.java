package ru.sber.beautifulcode.textchecker.utils;

import com.google.common.collect.BiMap;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CharacterPairCheckerUtil {

    public static boolean check(@NonNull final String text,
                                @NonNull final BiMap<Character, Character> pairs) {
        Deque<Character> deque = new LinkedList<>();
        var chars = text.toCharArray();
        for (int step = 0; step < chars.length; step++) {
            var element = chars[step];
            if (isFoundLeft(pairs, element)) {
                if (isNextElementValid(step, element, chars, pairs)) {
                    deque.push(element);
                } else {
                    return false;
                }
            } else if (isFoundRight(pairs, element)
                && (deque.isEmpty() ||
                isNotFoundPair(element, deque.pop(), pairs))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isFoundLeft(@NonNull final BiMap<Character, Character> pairs,
                                       final char element) {
        return pairs.containsKey(element);
    }

    private static boolean isNotFoundPair(final char element,
                                          @NonNull final Character left,
                                          @NonNull final BiMap<Character, Character> pairs) {
        return isNotPair(left, pairs.inverse().get(element));
    }

    private static boolean isFoundRight(@NonNull final BiMap<Character, Character> pairs,
                                        final char element) {
        return pairs.inverse().containsKey(element);
    }

    private static boolean isNextElementValid(final int step,
                                              final char currentElement,
                                              final char[] chars,
                                              BiMap<Character, Character> pairs) {
        var nextStep = Math.incrementExact(step);
        var nextElement = chars[nextStep];
        return nextStep < chars.length && isNotPair(pairs.get(currentElement), nextElement);
    }

    private static boolean isNotPair(@Nullable final Character expected, char actual) {
        return !Objects.equals(expected, actual);
    }
}