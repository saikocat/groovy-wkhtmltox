package com.saikocat.wkhtmltox.helper;

import groovy.transform.CompileStatic;
import groovy.transform.CompileDynamic;

import org.slf4j.Logger;

import com.saikocat.wkhtmltox.annotation.InjectLogger;

@CompileStatic
public interface StreamHelper {
    public String getError(InputStream input);
    public void copy(InputStream input, OutputStream output);
    public byte[] toByteArray(InputStream input);
}
