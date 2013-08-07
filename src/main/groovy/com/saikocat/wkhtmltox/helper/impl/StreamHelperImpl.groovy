package com.saikocat.wkhtmltox.helper.impl;

import com.google.inject.Inject;

import groovy.transform.CompileStatic;
import groovy.transform.CompileDynamic;

import org.slf4j.Logger;

import com.saikocat.wkhtmltox.annotation.InjectLogger;
import com.saikocat.wkhtmltox.helper.StreamHelper;

@CompileStatic
public class StreamHelperImpl implements StreamHelper {
    private final String DEFAULT_CHARSET = "UTF-8";`

    @InjectLogger
    Logger logger;

    public String getError(Process process) {
        return process.getErrorStream().withReader(DEFAULT_CHARSET) { Reader reader ->
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
