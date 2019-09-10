import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.github.eugenelesnov.NgramSearch.ngramSearch;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NgramSearchTest {

    @Nested
    class NgramSearch {

        @Test
        void shouldThrowIllegalStateExceptionWhenEmptySearchTerm() {
            // given
            int n = 3;
            String term = "";
            Collection<String> source = getStringCollection();

            // then
            assertThrows(IllegalStateException.class,
                    () -> ngramSearch(n, term, source));
        }

        @Test
        void shouldThrowIllegalStateExceptionWhenEmptySourceCollection() {
            // given
            int n = 3;
            String term = "term";
            Collection<String> source = new ArrayList<>();

            // then
            assertThrows(IllegalStateException.class,
                    () -> ngramSearch(n, term, source));
        }

        @Test
        void shouldThrowIllegalStateExceptionWhenIncorrectN() {
            // given
            int n = -3;
            String term = "term";
            Collection<String> source = getStringCollection();

            // then
            assertThrows(IllegalStateException.class,
                    () -> ngramSearch(n, term, source));
        }

        @Test
        void shouldReturnResultCollection() {
            // given
            int n = 3;
            String term = "eugene";
            Collection<String> source = getStringCollection();

            // then
            Map<String, Float> matched = ngramSearch(n, term, source);
            matched.forEach((k, v) -> System.out.println("Token: " + k + "; Value: " + v));
        }
    }

    private Collection<String> getStringCollection() {
        List<String> strings = new ArrayList<>();
        strings.add("Eugene");
        strings.add("Evgeny");
        strings.add("Evgeniy");
        strings.add("Evgeniya");
        strings.add("Evgesha");
        strings.add("Eureka");
        strings.add("Evgenius");
        strings.add("Julie");
        strings.add("MeganDorian");
        return strings;
    }
}
