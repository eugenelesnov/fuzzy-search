package com.github.eugenelesnov;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Common utility methods
 *
 * @author Eugene Lesnov
 */
class Util {

    /**
     * Method to normalize input string
     *
     * @param token input string
     * @return normalized string in lower case without
     * leading and trailing spaces
     */
    static String normalize(String token) {
        return token.toLowerCase().trim();
    }

    /**
     * Method to calculate match percentage for n-gram search
     *
     * @param tokenLength length of query string
     * @param resultSize  size of result Collection
     * @return percentage of match
     */
    static float getMatchPercentage(int tokenLength, int resultSize) {
        return Math.round(((float) resultSize / (float) tokenLength) * 100);
    }

    /**
     * Method to ordering map by descending {@link Float} value
     *
     * @param unorderedMap input map
     * @return ordered map by descending value
     */
    static <T> Map<T, Float> orderByDescValue(Map<T, Float> unorderedMap) {
        return unorderedMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (x, y) -> {
                            throw new IllegalStateException("Unexpected merge");
                        }, LinkedHashMap::new));
    }

    /**
     * Method to ordering map by ascending {@link Integer} value
     *
     * @param unorderedMap input map
     * @return ordered map by ascending value
     */
    static <T> Map<T, Integer> orderByAscValue(Map<T, Integer> unorderedMap) {
        LinkedHashMap<T, Integer> sortedMap = new LinkedHashMap<>();
        unorderedMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        return sortedMap;
    }

}
