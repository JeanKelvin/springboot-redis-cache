package com.example.ws;

import com.example.exception.AuthorNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AuthorExceptionHandler {

    private Logger log = LoggerFactory.getLogger(AuthorNotFoundException.class);


    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Author Not Found")
    @ExceptionHandler(AuthorNotFoundException.class)
    public void handleAuthorNotFound(HttpServletRequest request, Exception ex) {
        log.error("{} : {}", ex.getMessage(), request.getRequestURI());
    }
}
