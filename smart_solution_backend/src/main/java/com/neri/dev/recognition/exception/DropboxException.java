package com.neri.dev.recognition.exception;

public class DropboxException extends RuntimeException {

    public DropboxException(String message) {
        super(message);
    }

    public DropboxException(String message, Exception cause) {
        super(message, cause);
    }

}
