package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> homes, int topN) {
        return homes.stream()
                .sorted((o1, o2) -> o1.compareTo(o2))
                .limit(topN)
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}
// END
