package com.cni.exception;

public class NeomanException extends RuntimeException {
    public NeomanException() {
    }

    public NeomanException(String message) {
        super(message);
    }

    public NeomanException(String message, Throwable cause) {
        super(message, cause);
    }
}
