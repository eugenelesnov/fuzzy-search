import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static com.github.eugenelesnov.NgramSearch.ngramSearch;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
            assertThrows(IllegalStateException.class, () -> ngramSearch(power, token, source));
        }

        @Test
        void shouldThrowIllegalStateExceptionWhenEmptySourceCollection() {
            // given
            int power = 3;
            String token = "token";
            Collection<String> source = new ArrayList<>();

            // then
            assertThrows(IllegalStateException.class,
                    () -> ngramSearch(power, token, source));
        }

        @Test
        void shouldThrowIllegalStateExceptionWhenIncorrectPower() {
            // given
            int n = -3;
            String token = "term";
            Collection<String> source = TestUtil.getStringCollection();

            // then
            assertThrows(IllegalStateException.class, () -> ngramSearch(n, token, source));
        }

        @Test
        void shouldPrintResultMap() {
            // given
            int power = 3;
            String token = "eugene";
            Collection<String> source = TestUtil.getStringCollection();

            // then
            Map<String, Float> matched = ngramSearch(power, token, source);
            matched.forEach((k, v) -> System.out.println("Token: " + k + "; Percentage: " + v + "%"));

            assertNotNull(matched);
        }
    }

}
