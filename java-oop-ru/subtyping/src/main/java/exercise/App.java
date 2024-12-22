package exercise;

import java.util.HashMap;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        var map = new HashMap<String, String>();
        storage.toMap().forEach((key, value) -> {
            storage.unset(key);
            map.put(value, key);
        });
        map.forEach(storage::set);
    }
}
// END
