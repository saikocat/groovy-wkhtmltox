package com.saikocat.wkhtmltox.service;

import groovy.transform.CompileStatic;

@CompileStatic
public interface WkhtmltoxService {
    public void setExecutables(String pdfExecutable, String imageExecutable);
    public byte[] renderImage(URL url);
    public byte[] renderImage(File file);
    public byte[] renderPdf(URL url);
    public byte[] renderPdf(File file);
    public File renderToFile(File file, byte[] byteInput);
}
