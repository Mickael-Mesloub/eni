package fr.eni.tp.filmotheque.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FilmothequeControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public String handleException(HttpServletRequest req, Exception ex) throws Exception {
        return "view-erreur";
    }
}
