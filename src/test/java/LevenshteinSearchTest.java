import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.StringLength;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;

import static com.github.eugenelesnov.LevenshteinSearch.levenshtein;
import static com.github.eugenelesnov.LevenshteinSearch.levenshteinSearch;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LevenshteinSearchTest {

    @Property
    void distanceShouldNotDependOnTheOrder(@ForAll String string1, @ForAll String string2) {
        assertEquals(levenshtein(string1, string2), levenshtein(string2, string1));
    }

    @Property
    void distanceBetweenSameStringsShouldEqualToZero(@ForAll String string) {
        assertEquals(0, levenshtein(string, string));
    }

    @Property
    void distanceBetweenOriginalAndCroppedStringShouldEqualToCropSize(@ForAll @StringLength(min = 10) @AlphaChars String string,
                                                                      @ForAll @IntRange(max = 5) int startCropping,
                                                                      @ForAll @IntRange(max = 5) int endCropping) {
        String croppedString = string.substring(startCropping, string.length() - endCropping);
        assertEquals(startCropping + endCropping, levenshtein(string, croppedString));
    }

    @Test
    void shouldPrintResultMap() {
        // given
        int precision = 4;
        String token = "eugene";
        Collection<String> source = TestUtil.getStringCollection();

        // then
        Map<String, Integer> matched = levenshteinSearch(precision, token, source);
        matched.forEach((k, v) -> System.out.println("Token: " + k + "; Levenshtein distance: " + v));
    }
}
