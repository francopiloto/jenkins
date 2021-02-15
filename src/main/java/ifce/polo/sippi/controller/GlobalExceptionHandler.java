package ifce.polo.sippi.controller;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler
{

/* --------------------------------------------------------------------------------------------- */

    @ExceptionHandler
    public ResponseEntity<Object> handleException(HttpMessageNotReadableException e) {
        return badRequest().body("{\"error\":\"Missing required parameters\"}");
    }

/* --------------------------------------------------------------------------------------------- */

    @ExceptionHandler
    public ResponseEntity<Object> handleException(BadCredentialsException e) {
        return status(HttpStatus.UNAUTHORIZED).body("{\"error\":\"Invalid credentials\"}");
    }

/* --------------------------------------------------------------------------------------------- */

    @ExceptionHandler
    public ResponseEntity<Object> handleException(AccessDeniedException e) {
        return status(HttpStatus.FORBIDDEN).body("{\"error\":\"Restricted area\"}");
    }

/* --------------------------------------------------------------------------------------------- */

    @ExceptionHandler
    public ResponseEntity<Object> handleException(MissingServletRequestParameterException e) {
        return badRequest().body("{\"error\":\"Missing required query parameters\"}");
    }

/* --------------------------------------------------------------------------------------------- */

    @ExceptionHandler
    public ResponseEntity<Object> handleException(MethodArgumentTypeMismatchException e) {
        return badRequest().body("{\"error\":\"Invalid path parameters\"}");
    }

/* --------------------------------------------------------------------------------------------- */

    @ExceptionHandler
    public ResponseEntity<Object> handleException(Exception e) {
        return badRequest().build();
    }

/* --------------------------------------------------------------------------------------------- */

}
