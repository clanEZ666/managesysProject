package exception;

import java.io.IOException;

public class CorraptedFileDataException extends IOException {
    public CorraptedFileDataException(String message) {
        super(message);
    }
}
