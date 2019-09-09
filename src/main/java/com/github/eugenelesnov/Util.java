package com.github.eugenelesnov;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Fuzzy Search utility methods
 *
 * @author Eugene Lesnov
 */
class Util {

    /**
     * Method to normalize input string
     *
     * @param input input string
     * @return normalized string in lower case without
     * leading and trailing spaces
     */
    static String normalize(String input) {
        return input.toLowerCase().trim();
    }

    /**
     * Method to calculate match percentage
     *
     * @param termLength length of term String
     * @param resultSize size of result Collection
     * @return percentage of march
     */
    static float getMatchPercentage(int termLength, int resultSize) {
        return Math.round(((float) resultSize / (float) termLength) * 100);
    }

    /**
     * Method to ordering map by descending value
     *
     * @param unorderedMap input map
     * @return ordered map by descending value
     */
    static Map<String, Float> orderByDescValue(Map<String, Float> unorderedMap) {
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
