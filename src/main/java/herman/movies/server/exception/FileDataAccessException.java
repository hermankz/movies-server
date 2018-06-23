package herman.movies.server.exception;

import java.io.IOException;

/**
 * Created by herman.kurniawan on 6/23/2018.
 */
public class FileDataAccessException extends RuntimeException {
    public FileDataAccessException(IOException ioException) {
        super(ioException);
    }
}
