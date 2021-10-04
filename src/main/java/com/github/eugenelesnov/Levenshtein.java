package com.github.eugenelesnov;

import lombok.NonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.github.eugenelesnov.Util.normalize;
import static com.github.eugenelesnov.Util.orderByAscValue;

/**
 * Implementation of Levenshtein distance calculating
 *
 * @author Eugene Lesnov
 */
public class Levenshtein {

    /**
     * Method to search token in {@link Collection<T>}
     *
     * @param precision precision
     * @param token     search token
     * @param source    collection for searching
     * @param function  functional interface describing the way to get string
     * @return map with a {@link T} as a key and precision (Levenshtein distance) as a value
     */
    public static <T> Map<T, Integer> levenshteinSearch(int precision,
                                                        @NonNull String token,
                                                        @NonNull Collection<T> source,
                                                        Function<T, String> function) {

        if (token.isEmpty()) {
            throw new IllegalStateException("Search token must not be empty");
        }

        if (source.isEmpty()) {
            return Collections.emptyMap();
        }

        if (precision <= 0) {
            throw new IllegalStateException("The precision must be positive");
        }

        Map<T, Integer> matched = new HashMap<>();

        source.forEach(s -> {
            String appliedToken = function.apply(s);
            int distance = levenshtein(appliedToken, token);
            if (distance <= precision) {
                matched.put(s, distance);
            }
        });
        return orderByAscValue(matched);
    }

    /**
     * Method to calculate Levenshtein distance between two strings
     *
     * @param str1 first string
     * @param str2 second string
     * @return Levenshtein distance
     */
    public static int levenshtein(@NonNull String str1, @NonNull String str2) {

        if (str1.equals(str2)) {
            return 0;
        }

        str1 = normalize(str1);
        str2 = normalize(str2);

        int str1Length = str1.length();
        int str2Length = str2.length();

        int[] cost = new int[str2.length() + 1];

        for (int j = 0; j < cost.length; j++) {
            cost[j] = j;
        }

        for (int i = 1; i <= str1Length; i++) {
            cost[0] = i;
            int nw = i - 1;

            for (int j = 1; j <= str2Length; j++) {
                int cj;
                int min = Math.min(cost[j], cost[j - 1]);

                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    cj = Math.min(1 + min, nw);
                } else {
                    cj = Math.min(1 + min, nw + 1);
                }

                nw = cost[j];
                cost[j] = cj;
            }
        }

        return cost[str2Length];
    }
}
