package com.github.eugenelesnov;

import lombok.NonNull;

import java.util.*;
import java.util.stream.Collectors;

import static com.github.eugenelesnov.FuzzyUtil.ngramFrom;

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

            float matchPercentage = Math.round(((float) result.size() / (float) termSize) * 100);
            matched.put(s, matchPercentage);
        });
        return orderByDescValue(matched);
    }

    /**
     * Method to normalize input string
     *
     * @param input input string
     * @return normalized string in lower case without
     * leading and trailing spaces
     */
    private static String normalize(String input) {
        return input.toLowerCase().trim();
    }

    /**
     * Method to ordering map by descending value
     *
     * @param unorderedMap input map
     * @return ordered map by descending value
     */
    private static Map<String, Float> orderByDescValue(Map<String, Float> unorderedMap) {
        return unorderedMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (x, y) -> {
                            throw new IllegalStateException("Unexpected merge");
                        }, LinkedHashMap::new));
    }

}
