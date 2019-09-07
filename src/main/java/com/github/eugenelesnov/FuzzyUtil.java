package com.github.eugenelesnov;

import java.util.ArrayList;
import java.util.List;

/**
 * Fuzzy Search utility methods
 *
 * @author Eugene Lesnov
 */
class FuzzyUtil {

    /**
     * Method to create ngrams from input string
     *
     * @param n   power of n-gram
     * @param str input string
     * @return list of ngrams
     */
    static List<String> ngramFrom(int n, String str) {
        List<String> ngrams = new ArrayList<>();
        for (int i = 0; i < str.length() - n + 1; i++)
            ngrams.add(str.substring(i, i + n));
        return ngrams;
    }

}
