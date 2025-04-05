package lumaautomation.behaviors.utility;

import net.serenitybdd.screenplay.Question;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class DoHelper {

    // Helper Function: Checks if items in a list are unique.
    // See: https://stackoverflow.com/questions/562894/detect-duplicates-in-arraylist
    public static <T> Question<Boolean> areListValuesUnique(final Stream<T> stream) {
        return actor -> {
            final Set<T> seen = new HashSet<>();
            return stream.allMatch(seen::add);
        };
    }
}
