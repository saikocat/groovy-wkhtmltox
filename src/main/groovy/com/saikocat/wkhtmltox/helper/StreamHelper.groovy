package com.saikocat.wkhtmltox.helper;

import groovy.transform.CompileStatic;
import groovy.transform.CompileDynamic;

import org.slf4j.Logger;

import com.saikocat.wkhtmltox.annotation.InjectLogger;

@CompileStatic
public interface StreamHelper {
    @InjectLogger Logger logger;

    public String getError(Process process);
    public void copy(InputStream input, OutputStream output);
    public byte[] toByteArray(InputStream input);
}
