package com.neri.dev.recognition.exception;

@FunctionalInterface
public interface DropboxActionResolver<T> {

    T perform() throws Exception;

}
