package at.fhhgb.mc.shared;

public class ValueException extends Exception {
    public <T> ValueException(T value) {
        super("Invalid Value: " + value);
    }
}
