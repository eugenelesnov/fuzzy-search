package unit;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;

import static com.github.eugenelesnov.LevenshteinSearch.levenshtein;
import static com.github.eugenelesnov.LevenshteinSearch.levenshteinSearch;
import static org.junit.jupiter.api.Assertions.*;

class LevenshteinSearchTest {

    @Test
    void shouldThrowNullPointerException() {
        String str1 = null;
        String str2 = null;

        assertThrows(NullPointerException.class, () -> levenshtein(str1, str2));
    }

    @Test
    void shouldReturnZero() {
        String str1 = "test";
        String str2 = "test";

        int distance = levenshtein(str1, str2);
        assertEquals(0, distance);
    }

    @Test
    void shouldReturnLevenshteinDistance() {
        String str1 = "test";
        String str2 = "testtest";
        // expected distance is 4 because difference between
        // str1 and str2 is 4 actions of character deletions
        int expectedDistance = 4;

        int actualDistance = levenshtein(str1, str2);
        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    void shouldPrintResultMap() {
        int precision = 4;
        String token = "eugene";
        Collection<String> source = TestUtil.getStringCollection();

        Map<String, Integer> result = levenshteinSearch(precision, token, source, String::toString);

        assertNotNull(result);
        result.forEach((k, v) -> System.out.println("Token: " + k + "; Levenshtein distance: " + v));
    }
}
