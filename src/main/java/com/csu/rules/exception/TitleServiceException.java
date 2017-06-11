package com.csu.rules.exception;

/**
 * Created by ltaoj on 17-6-11.
 */
public class TitleServiceException extends ServiceException {

    public TitleServiceException() {
    }

    public TitleServiceException(String message) {
        super(message);
    }

    public TitleServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TitleServiceException(Throwable cause) {
        super(cause);
    }

    public TitleServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
