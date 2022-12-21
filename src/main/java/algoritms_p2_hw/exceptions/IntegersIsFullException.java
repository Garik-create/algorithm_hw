package algoritms_p2_hw.exceptions;

public class IntegersIsFullException extends RuntimeException {
    public IntegersIsFullException() {
    }

    public IntegersIsFullException(String message) {
        super(message);
    }

    public IntegersIsFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public IntegersIsFullException(Throwable cause) {
        super(cause);
    }

    public IntegersIsFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
