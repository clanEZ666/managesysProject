package exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CorraptedFileDataException extends IOException {
    private static final Logger log = LoggerFactory.getLogger(CorraptedFileDataException.class);

    public CorraptedFileDataException(String message) {
        super(message);
        log.warn(message);
    }
}
