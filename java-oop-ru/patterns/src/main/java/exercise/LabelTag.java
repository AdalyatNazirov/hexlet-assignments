package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private final String label;
    private final TagInterface tag;

    public LabelTag(String label, TagInterface tag) {
        this.label = label;
        this.tag = tag;
    }

    @Override
    public String render() {
        return String.format("<label>%s%s</label>", label, tag.render());
    }
}
// END
