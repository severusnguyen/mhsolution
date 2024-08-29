package com.mh.core.mhsconfig.config.exception;

import com.mh.core.mhscommons.core.exception.DBException;
import com.mh.core.mhscommons.data.model.DfResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DBExceptionHandler {

    @ExceptionHandler(DBException.class)
    public ResponseEntity<DfResponse> TodoException(Exception exception){
        return ResponseEntity.badRequest().body(DfResponse.badRequest(exception.getMessage()));
    }

}
