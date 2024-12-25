package exercise;

// BEGIN
public class Circle {
    private int radius;
    private Point center;

    public Circle(Point center, int radius) {
        this.radius = radius;
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException();
        }
        return Math.PI * radius * radius;
    }
}
// END
