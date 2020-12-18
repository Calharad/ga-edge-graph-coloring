package pl.calharad.ga.graph.props;

public class PropertyException extends RuntimeException {
    public PropertyException() {
    }

    public PropertyException(String message) {
        super(message);
    }

    public PropertyException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyException(Throwable cause) {
        super(cause);
    }
}
