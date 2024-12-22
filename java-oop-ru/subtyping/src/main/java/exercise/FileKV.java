package exercise;

import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {

    private final String path;

    public FileKV(String path, Map<String, String> map) {
        this.path = path;
        Utils.writeFile(path, Utils.serialize(map));
    }

    @Override
    public void set(String key, String value) {
        var map = Utils.deserialize(Utils.readFile(path));
        map.put(key, value);
        Utils.writeFile(path, Utils.serialize(map));
    }

    @Override
    public void unset(String key) {
        var map = Utils.deserialize(Utils.readFile(path));
        map.remove(key);
        Utils.writeFile(path, Utils.serialize(map));
    }

    @Override
    public String get(String key, String defaultValue) {
        var map = Utils.deserialize(Utils.readFile(path));
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return Utils.deserialize(Utils.readFile(path));
    }
}
// END
