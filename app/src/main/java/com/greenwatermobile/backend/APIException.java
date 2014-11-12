package com.greenwatermobile.backend;

public class APIException extends Exception {

    public APIException(Throwable t) {
        super("API request failed", t);
    }
}
