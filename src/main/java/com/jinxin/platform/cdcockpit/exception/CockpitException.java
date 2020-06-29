package com.jinxin.platform.cdcockpit.exception;

/**
 * @author Huang LingSong
 */
public class CockpitException extends RuntimeException {

    public CockpitException() {
        super();
    }

    public CockpitException(String message) {
        super(message);
    }

    public CockpitException(String message, Throwable cause) {
        super(message, cause);
    }
}
