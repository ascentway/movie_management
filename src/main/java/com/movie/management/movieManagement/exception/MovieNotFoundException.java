package com.movie.management.movieManagement.exception;

public class MovieNotFoundException extends RuntimeException{
    protected MovieNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MovieNotFoundException(Throwable cause) {
        super(cause);
    }

    public MovieNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieNotFoundException(String message) {
        super(message);
    }

    public MovieNotFoundException() {
        super();
    }
}
