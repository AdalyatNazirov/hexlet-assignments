package exercise;

// BEGIN
public class NegativeRadiusException extends Exception {
    public NegativeRadiusException() {
        super("Negative radius");
    }

    public NegativeRadiusException(String message) {
        super(message);
    }
}
// END
