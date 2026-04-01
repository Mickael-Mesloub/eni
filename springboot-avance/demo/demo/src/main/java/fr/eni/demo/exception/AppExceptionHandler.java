package fr.eni.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> capturerException(MethodArgumentNotValidException e) {
       String message = e.getFieldErrors().stream().map(err -> err.getField() + " - " + err.getDefaultMessage()).collect(Collectors.joining(" / "));
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message);
    }
}
