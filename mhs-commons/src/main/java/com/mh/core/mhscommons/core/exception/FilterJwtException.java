package com.mh.core.mhscommons.core.exception;

public class FilterJwtException extends RuntimeException {
    public FilterJwtException() {
    }

    public FilterJwtException(String message) {
        super(message);
    }

    public FilterJwtException(String message, Throwable cause) {
        super(message, cause);
    }
}
