package com.saikocat.wkhtmltox.service;

import groovy.transform.CompileStatic;

@CompileStatic
public interface AsyncWkhtmltoxService {
    public void setExecutables(String pdfExecutable, String imageExecutable);
    public String queue(Closure closure);
    public byte[] output(String token);
    public boolean isDone(String token);

    public String renderImage(URL url);
    public String renderImage(File file);
    public String renderPdf(URL url);
    public String renderPdf(File file);
    public File renderToFile(File file, byte[] byteInput);
}
