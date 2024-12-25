package exercise;

import java.util.List;
import java.util.Map;

// BEGIN
public final class PairedTag extends Tag {
    private final String content;
    private final List<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String content, List<Tag> children) {
        super(name, attributes);
        this.content = content;
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(content);
        children.forEach(sb::append);
        sb.append("</" + getName() + ">");
        return sb.toString();
    }
}
// END
