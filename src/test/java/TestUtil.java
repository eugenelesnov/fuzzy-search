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
