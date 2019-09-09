package com.github.eugenelesnov;

import lombok.NonNull;

import java.util.*;
import java.util.stream.Collectors;

import static com.github.eugenelesnov.Util.*;

/**
 * Fuzzy Search methods
 *
 * @author Eugene Lesnov
 */
public class FuzzySearch {

    /**
     * Method to search token in {@link Collection<String>}
     *
     * @param n      power of n-gram
     * @param token  search token
     * @param source collection for searching
     * @return map with a token as a key and precision (percentage) as a value
     */
    public static Map<String, Float> ngramSearch(int n, @NonNull String token, @NonNull Collection<String> source) {

        if (token.isEmpty()) {
            throw new IllegalStateException("Search token must not be empty");
        }

        if (source.isEmpty()) {
            throw new IllegalStateException("Source collection must not be empty");
        }

        if (n <= 0) {
            throw new IllegalStateException("The argument n must be >= 0");
        }

        List<String> termNgrams = new ArrayList<>(ngramFrom(n, normalize(token)));
        int termSize = termNgrams.size();
        Map<String, Float> matched = new HashMap<>();

        source.forEach(s -> {
            String normalized = normalize(s);
            List<String> currentNgrams = new ArrayList<>(ngramFrom(n, normalized));

            List<String> result = currentNgrams.stream()
                    .filter((new ArrayList<>(termNgrams)::contains))
                    .collect(Collectors.toList());

            float matchPercentage = getMatchPercentage(termSize, result.size());
            matched.put(s, matchPercentage);
        });
        return orderByDescValue(matched);
    }

    /**
     * Method to create ngrams from input string
     *
     * @param n   power of n-gram
     * @param str input string
     * @return list of ngrams
     */
    private static List<String> ngramFrom(int n, String str) {
        List<String> ngrams = new ArrayList<>();
        for (int i = 0; i < str.length() - n + 1; i++)
            ngrams.add(str.substring(i, i + n));
        return ngrams;
    }

}
