package io.portfolio.ecommerce.exception;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {
    /**
     * Exception handler method for ConstraintViolationException.
     * It creates ErrorItem objects for each violation, sets error codes and messages,
     * adds them to an ErrorResponse, and returns a ResponseEntity with the errors and a BAD_REQUEST status.
     */
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handle(ConstraintViolationException e) {
        ErrorResponse errors = new ErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            ErrorItem error = new ErrorItem();
            error.setCode(violation.getMessageTemplate());
            error.setMessage(violation.getMessage());
            errors.addError(error);
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler method for ResourceNotFoundException.
     * Creates an ErrorItem with the exception message and returns a ResponseEntity with the ErrorItem and a NOT_FOUND status.
     *
     * @param e The ResourceNotFoundException to handle
     * @return ResponseEntity containing the ErrorItem with the exception message and a NOT_FOUND status
     */
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorItem> handle(ResourceNotFoundException e) {
        ErrorItem error = new ErrorItem();
        error.setMessage(e.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Represents an error item with code and message fields.
     *
     * This class is used to encapsulate error information, where 'code' represents a specific error code
     * and 'message' provides a human-readable description of the error.
     *
     * The 'code' field is annotated with @JsonInclude(JsonInclude.Include.NON_NULL) to indicate that it should be included
     * in JSON serialization only if it is not null.
     *
     * The getter and setter methods for 'code' and 'message' allow access to and modification of these fields.
     */
    public static class ErrorItem {

        @JsonInclude(JsonInclude.Include.NON_NULL) private String code;

        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

    /**
     * Represents a response containing a list of ErrorItem objects.
     */
    public static class ErrorResponse {
        //List of ErrorItem objects
        private List<ErrorItem> errors = new ArrayList<>();

        public List<ErrorItem> getErrors() {
            return errors;
        }

        public void setErrors(List<ErrorItem> errors) {
            this.errors = errors;
        }

        public void addError(ErrorItem error) {
            this.errors.add(error);
        }

    }
}
