package com.github.srec;

/**
 * @author Victor Tatai
 */
public class MainMethodRunningException extends SRecException {
    public MainMethodRunningException(String message) {
        super(message);
    }

    public MainMethodRunningException(Throwable cause) {
        super(cause);
    }
}
