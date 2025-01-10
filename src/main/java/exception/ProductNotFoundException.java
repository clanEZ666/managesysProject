package exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductNotFoundException extends RuntimeException{
    private static final Logger log = LoggerFactory.getLogger(ProductNotFoundException.class);

    public ProductNotFoundException(String message ) {
        super(message);
        log.warn(message);
    }
}
