package exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
    super(message);
    log.warn(message);
    }
}