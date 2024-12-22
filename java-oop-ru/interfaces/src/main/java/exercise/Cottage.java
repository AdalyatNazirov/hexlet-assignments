package exercise;

// BEGIN
public class Cottage implements Home {

    private final double area;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    private final int floorCount;

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public String toString() {
        return floorCount + " этажный коттедж площадью " + getArea() + " метров";
    }
}
// END
