package com.myblog.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends  RuntimeException {
    private String message;
    private HttpStatus status;

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public BlogAPIException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
