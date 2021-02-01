package com.manager.crudservice.configuration;

import com.manager.crudservice.exception.InfrastructureException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { InfrastructureException.class, DataIntegrityViolationException.class, TransactionSystemException.class })
    protected ResponseEntity<Object> handlerInfrastructureException(RuntimeException ex, WebRequest request) {
        if (ex instanceof TransactionSystemException) {
            if (ex.getCause().getCause() instanceof InfrastructureException) {
                ex = (RuntimeException) ex.getCause().getCause();
            }
        }

        ex.printStackTrace();
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    protected ResponseEntity<Object> constraintValidation(ConstraintViolationException ex, WebRequest request) {
        String bodyOfResponse = ex.getConstraintViolations().stream()
                .findFirst()
                .get().getMessage();

        ex.printStackTrace();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { ExpiredJwtException.class, SignatureException.class })
    protected ResponseEntity<Object> handleExpiredJwtException(RuntimeException ex, WebRequest request) {
        if(ex instanceof SignatureException)
            return handleExceptionInternal(ex, "Operação negada, token inválido.", new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);

        if(ex instanceof ExpiredJwtException)
            return handleExceptionInternal(ex, "Operação negada, login expirado.", new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);

        ex.printStackTrace();
        return handleExceptionInternal(ex, "Operação negada, " + ex.getMessage(), new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }
}
