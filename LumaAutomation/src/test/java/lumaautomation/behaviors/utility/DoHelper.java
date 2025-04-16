package lumaautomation.behaviors.utility;

import net.serenitybdd.screenplay.Question;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/***
 * A Helper Behavior.
 * Place any helper code without any clear association with the Browser.
 */
public class DoHelper {

    /***
     * Helper Function: Checks if items in a list are unique.
     * Reference: https://stackoverflow.com/questions/562894/detect-duplicates-in-arraylist
     * @param stream A list as a Stream.
     * @return Boolean. True if List Values are unique.
     * @param <T> Type of Stream (i.e. List Type).
     */
    public static <T> Question<Boolean> areListValuesUnique(final Stream<T> stream) {
        return actor -> {
            final Set<T> seen = new HashSet<>();
            return stream.allMatch(seen::add);
        };
    }
}
