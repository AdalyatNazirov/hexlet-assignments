package exercise;

// BEGIN
public interface Home {
    double getArea();

    default int compareTo(Home other) {
        return Double.compare(this.getArea(), other.getArea());
    }
}
// END
