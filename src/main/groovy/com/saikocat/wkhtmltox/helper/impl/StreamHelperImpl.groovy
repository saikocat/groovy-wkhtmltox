package com.saikocat.wkhtmltox.helper.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import groovy.transform.CompileStatic;
import groovy.transform.CompileDynamic;

import org.slf4j.Logger;

import com.saikocat.wkhtmltox.annotation.InjectLogger;
import com.saikocat.wkhtmltox.helper.StreamHelper;

@CompileStatic
public class StreamHelperImpl implements StreamHelper {
    @InjectLogger
    Logger logger;

    final String charset;

    @Inject
    public StreamHelperImpl(@Named("DefaultCharset") String charset) {
        this.charset = charset;
    }

    public String getError(InputStream input) {
        return input.withReader(charset) { Reader reader ->
            reader.getText();
        }
    }

    public void copy(InputStream input, OutputStream output) {
        output << input;
    }

    public byte[] toByteArray(InputStream input) {
        OutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }
}
