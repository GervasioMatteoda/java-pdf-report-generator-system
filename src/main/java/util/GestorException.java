package util;

public class GestorException extends RuntimeException {

    public GestorException(String message) {
        super(message);
    }

    public GestorException(String message, Throwable cause) {
        super(message, cause);
    }
}