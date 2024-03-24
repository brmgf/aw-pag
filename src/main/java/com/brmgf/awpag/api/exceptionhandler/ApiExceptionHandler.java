package com.brmgf.awpag.api.exceptionhandler;

import com.brmgf.awpag.domain.exception.AwpagException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AwpagException.class)
    public ResponseEntity<String> capturarExcecoes(AwpagException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
