package exercise;

import java.util.Map;

// BEGIN
public abstract class Tag {
    private final String name;
    private final Map<String, String> attributes;

    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        var attrs = attributes.entrySet().stream()
                .reduce("",
                        (a, e) -> a + String.format(" %s=\"%s\"", e.getKey(), e.getValue()),
                        (a, b) -> a + b);
        return String.format("<%s%s>", name, attrs);
    }
}
// END
