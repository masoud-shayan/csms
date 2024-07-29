package com.chargepoint.csms.transactionservice.charging.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionInfo> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        log.warn("An exception occurred, which will cause a {} response", HttpStatus.BAD_REQUEST, ex);

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).toList();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionInfo.builder()
                        .timeStamp(Instant.now().toString())
                        .status(HttpStatus.BAD_REQUEST)
                        .message(ex.getMessage())
                        .errors(errors).build());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionInfo> handleAnyOtherException(Exception ex) {

        log.error("An exception occurred, which will cause a {} response", HttpStatus.INTERNAL_SERVER_ERROR, ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionInfo.builder()
                        .timeStamp(Instant.now().toString())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(ex.getMessage())
                        .errors(Collections.singletonList(ex.getMessage())).build());
    }
}