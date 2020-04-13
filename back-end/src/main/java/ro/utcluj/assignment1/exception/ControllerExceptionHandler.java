package ro.utcluj.assignment1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.utcluj.assignment1.model.ErrorResponse;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse resourceNotFoundHandler(ResourceNotFoundException exception) {
        return new ErrorResponse(exception.getMessage(), getCause(exception));
    }

   /* @ExceptionHandler(OperationFailedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse operationFailedHandler(OperationFailedException exception) {
        return new ErrorResponse(exception.getMessage(), getCause(exception));
    }*/

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse generalException(Exception exception) {
        return new ErrorResponse(exception.getMessage(), getCause(exception));
    }

    private String getCause(Exception e) {
        return e.getCause() == null ? e.getClass().getName() : e.getCause().getMessage();
    }

}
