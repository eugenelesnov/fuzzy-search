import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.github.eugenelesnov.LevenshteinDistance.levenshtein;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LevenshteinDistanceTest {

    @Nested
    class Levenshtein {

        @Test
        void shouldReturnZero() {
            // given
            String str1 = "test";
            String str2 = "test";

            // then
            int distance = levenshtein(str1, str2);
            assertEquals(0, distance);
        }

        @Test
        void shouldReturnLevenshteinDistance() {
            // given
            String str1 = "test";
            String str2 = "testtest";
            // expected distance is 4 because difference between
            // str1 and str2 is 4 actions of character deletions
            int expectedDistance = 4;

            // then
            int actualDistance = levenshtein(str1, str2);
            assertEquals(expectedDistance, actualDistance);
        }
    }
}
