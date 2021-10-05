package com.github.eugenelesnov;

import lombok.NonNull;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.github.eugenelesnov.Util.*;

/**
 * N-gram search implementation
 *
 * @author Eugene Lesnov
 */
public class Ngram {

    /**
     * Method to search token in {@link Collection<T>}
     *
     * @param power           power of n-grams
     * @param matchPercentage desired percentage of match (value between 0 and 100)
     * @param token           search token
     * @param source          collection for searching
     * @param function        functional interface describing the way to get string
     * @return map with a {@link T} as a key and precision (Levenshtein distance) as a value
     */
    public static <T> Map<T, Float> ngramSearch(int power, float matchPercentage,
                                                @NonNull String token,
                                                @NonNull Collection<T> source, Function<T, String> function) {

        if (token.isEmpty()) {
            throw new IllegalStateException("Search token must not be empty");
        }

        if (source.isEmpty()) {
            return Collections.emptyMap();
        }

        if (power <= 0) {
            throw new IllegalStateException("The power of n-grams must be positive");
        }

        List<String> tokenNgrams = ngram(power, normalize(token));
        int tokenSize = tokenNgrams.size();
        Map<T, Float> matched = new HashMap<>();

        source.forEach(t -> {
            String sourceToken = function.apply(t);
            String normalized = normalize(sourceToken);
            List<String> currentNgrams = ngram(power, normalized);

            List<String> result = currentNgrams.stream()
                    .filter((new ArrayList<>(tokenNgrams)::contains))
                    .collect(Collectors.toList());

            float tokenMatchPercentage = getMatchPercentage(tokenSize, result.size());

            if (tokenMatchPercentage >= matchPercentage) {
                matched.put(t, tokenMatchPercentage);
            }
        });
        return orderByDescValue(matched);
    }

    /**
     * Method to create n-grams from input string
     *
     * @param n   power of n-gram
     * @param str input string
     * @return List of n-grams
     */
    private static List<String> ngram(int n, String str) {
        return IntStream.range(0, str.length() - n + 1)
                .mapToObj(i -> str.substring(i, i + n))
                .collect(Collectors.toList());
    }

}
