package unit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Test utility methods
 *
 * @author Eugene Lesnov
 */
class TestUtil {

    static Collection<String> getStringCollection() {
        List<String> strings = new ArrayList<>();
        strings.add("Eugene");
        strings.add("Eugene");
        strings.add("Evgeny");
        strings.add("Evgeniy");
        strings.add("Evgeniya");
        strings.add("Evgesha");
        strings.add("Eureka");
        strings.add("Evgenius");
        strings.add("Finrod Felagund");
        strings.add("Glorfindel");
        return strings;
    }
}
