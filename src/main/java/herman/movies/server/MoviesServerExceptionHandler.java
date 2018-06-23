package herman.movies.server;

import herman.movies.server.exception.EntityAlreadyExistsException;
import herman.movies.server.exception.EntityNotFoundException;
import herman.movies.server.exception.FileDataAccessException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by herman.kurniawan on 6/23/2018.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RestController
public class MoviesServerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(FileDataAccessException.class)
    protected ResponseEntity<Object> handleFileDataAccess(FileDataAccessException ex, WebRequest request) {
        String bodyOfResponse = "File data access error - " + ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    protected ResponseEntity<Object> handleEntityAlreadyExists(EntityAlreadyExistsException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }
}
