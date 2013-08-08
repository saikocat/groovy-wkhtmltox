package com.saikocat.wkhtmltox.exception;

import groovy.transform.CompileStatic;
import groovy.transform.CompileDynamic;

@CompileStatic
public class WkhtmltoxException extends Exception {
    public WkhtmltoxException() {
        super();
    }

    public WkhtmltoxException(String message) {
        super(message);
    }

    public WkhtmltoxException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public WkhtmltoxException(Throwable throwable) {
        super(throwable);
    }
}
