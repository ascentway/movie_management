package com.movie.management.movieManagement.exception;

public class NoMovieFoundException extends RuntimeException{
    public NoMovieFoundException() {
    }

    public NoMovieFoundException(String message) {
        super(message);
    }

    public NoMovieFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMovieFoundException(Throwable cause) {
        super(cause);
    }

    public NoMovieFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
