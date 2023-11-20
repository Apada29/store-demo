package com.ing.demos.web.exception;

public class UserBadCredentialsException extends RuntimeException {

    public UserBadCredentialsException() {
        super();
    }

    public UserBadCredentialsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserBadCredentialsException(final String message) {
        super(message);
    }

    public UserBadCredentialsException(final Throwable cause) {
        super(cause);
    }
}