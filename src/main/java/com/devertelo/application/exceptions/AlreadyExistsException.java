package com.devertelo.application.exceptions;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(RuntimeException runtimeException) {
        super(runtimeException);
    }

}
