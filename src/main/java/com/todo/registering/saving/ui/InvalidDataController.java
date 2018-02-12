package com.todo.registering.saving.ui;

import com.todo.common.validation.ContractBroken;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InvalidDataController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ContractBroken.class })
    protected ResponseEntity<Object> handleInvalidRegistrationData(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "You've provided wrong registration data.";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
