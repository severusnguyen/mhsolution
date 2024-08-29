package com.mh.core.mhsconfig.config.exception;


import com.mh.core.mhscommons.data.model.DfResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<DfResponse> TodoException(ApiException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.valueOf(ex.getCode())).body(DfResponse.customRequest(ex.getMessage(), ex.getCode()));
    }
}
