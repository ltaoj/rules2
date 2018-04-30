package com.csu.rules.exception;

/**
 * Created by ltaoj on 17-7-4.
 */
public class SchoolServiceException extends ServiceException {
    public SchoolServiceException() {
    }

    public SchoolServiceException(String message) {
        super(message);
    }

    public SchoolServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SchoolServiceException(Throwable cause) {
        super(cause);
    }

    public SchoolServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
