package com.github.eugenelesnov;

import lombok.NonNull;

import java.util.*;
import java.util.stream.Collectors;

import static com.github.eugenelesnov.Util.*;

/**
 * Ngram algorithm search implementation
 *
 * @author Eugene Lesnov
 */
public class NgramSearch {

    /**
     * Method to search token in {@link Collection<String>}
     *
     * @param power  power of n-grams
     * @param token  search token
     * @param source collection for searching
     * @return map with a token as a key and precision (percentage) as a value
     */
    public static Map<String, Float> ngramSearch(int power,
                                                 @NonNull String token,
                                                 @NonNull Collection<String> source) {

        if (token.isEmpty()) {
            throw new IllegalStateException("Search token must not be empty");
        }

        if (source.isEmpty()) {
            throw new IllegalStateException("Source collection must not be empty");
        }

        if (power <= 0) {
            throw new IllegalStateException("The power of n-grams must be > 0");
        }

        List<String> tokenNgrams = new ArrayList<>(ngram(power, normalize(token)));
        int tokenSize = tokenNgrams.size();
        Map<String, Float> matched = new HashMap<>();

        source.forEach(sourceToken -> {
            String normalized = normalize(sourceToken);
            List<String> currentNgrams = new ArrayList<>(ngram(power, normalized));

            List<String> result = currentNgrams.stream()
                    .filter((new ArrayList<>(tokenNgrams)::contains))
                    .collect(Collectors.toList());

            float matchPercentage = getMatchPercentage(tokenSize, result.size());
            matched.put(sourceToken, matchPercentage);
        });
        return orderByDescValue(matched);
    }

    /**
     * Method to create n-grams from input string
     *
     * @param n   power of n-gram
     * @param str input string
     * @return list of ngrams
     */
    public static List<String> ngram(int n, String str) {
        List<String> ngrams = new ArrayList<>();
        for (int i = 0; i < str.length() - n + 1; i++)
            ngrams.add(str.substring(i, i + n));
        return ngrams;
    }

}
