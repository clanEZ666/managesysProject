package exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderNotFoundException extends RuntimeException {
    private static final Logger log = LoggerFactory.getLogger(OrderNotFoundException.class);

    public OrderNotFoundException(String message) {
        super(message);
        log.warn(message);
    }
}
