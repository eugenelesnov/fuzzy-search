package unit;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static com.github.eugenelesnov.NgramSearch.ngramSearch;
import static org.junit.jupiter.api.Assertions.*;

class NgramSearchTest {

    @Nested
    class NgramSearch {

        @Test
        void shouldThrowIllegalStateExceptionWhenEmptySearchTerm() {
            // given
            int power = 3;
            String token = "";
            Collection<String> source = TestUtil.getStringCollection();

            // then
            assertThrows(IllegalStateException.class, () -> ngramSearch(power, 20f, token, source, String::toString));
        }

        @Test
        void shouldReturnEmptyMapWhenEmptySourceCollection() {
            // given
            int power = 3;
            String token = "token";
            Collection<String> source = new ArrayList<>();

            // then
            Map<String, Float> actualResult = ngramSearch(power, 20f, token, source, String::toString);
            assertTrue(actualResult.isEmpty());
        }

        @Test
        void shouldThrowIllegalStateExceptionWhenIncorrectPower() {
            // given
            int n = -3;
            String token = "term";
            Collection<String> source = TestUtil.getStringCollection();

            // then
            assertThrows(IllegalStateException.class, () -> ngramSearch(n, 20f, token, source, String::toString));
        }

        @Test
        void shouldPrintResultMap() {
            // given
            int power = 2;
            String token = "eugene";
            Collection<String> source = TestUtil.getStringCollection();

            // then
            Map<String, Float> result = ngramSearch(power, 40f, token, source, String::toString);
            assertNotNull(result);

            result.forEach(((k, v) -> System.out.println("Token: " + k + "; Match percentage: " + v + "% ;")));

        }
    }

}
