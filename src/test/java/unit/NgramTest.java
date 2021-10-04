package unit;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static com.github.eugenelesnov.Ngram.ngramSearch;
import static org.junit.jupiter.api.Assertions.*;

class NgramTest {

    @Nested
    class Ngram {

        @Test
        void shouldThrowIllegalStateExceptionWhenEmptySearchTerm() {
            int power = 3;
            String token = "";
            Collection<String> source = TestUtil.getStringCollection();

            assertThrows(IllegalStateException.class, () -> ngramSearch(power, 20f, token, source, String::toString));
        }

        @Test
        void shouldReturnEmptyMapWhenEmptySourceCollection() {
            int power = 3;
            String token = "token";
            Collection<String> source = new ArrayList<>();

            Map<String, Float> actualResult = ngramSearch(power, 20f, token, source, String::toString);
            assertTrue(actualResult.isEmpty());
        }

        @Test
        void shouldThrowIllegalStateExceptionWhenIncorrectPower() {
            int n = -3;
            String token = "term";
            Collection<String> source = TestUtil.getStringCollection();

            assertThrows(IllegalStateException.class, () -> ngramSearch(n, 20f, token, source, String::toString));
        }

        @Test
        void shouldPrintResultMap() {
            int power = 2;
            String token = "eugene";
            Collection<String> source = TestUtil.getStringCollection();

            Map<String, Float> result = ngramSearch(power, 40f, token, source, String::toString);
            assertNotNull(result);

            result.forEach(((k, v) -> System.out.println("Token: " + k + "; Match percentage: " + v + "% ;")));
        }
    }

}
