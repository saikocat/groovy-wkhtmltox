package com.saikocat.wkhtmltox.service;

import groovy.transform.CompileStatic;
import com.saikocat.wkhtmltox.renderer.PdfRenderer;
import com.saikocat.wkhtmltox.renderer.ImageRenderer;

@CompileStatic
public interface WkhtmltoxService {
    public void setExecutables(String pdfExecutable, String imageExecutable);
    public void setImageRenderer(ImageRenderer renderer);
    public void setPdfRenderer(PdfRenderer renderer);
    public ImageRenderer getImageRenderer();
    public PdfRenderer getPdfRenderer();
    public byte[] renderImage(URL url);
    public byte[] renderImage(File file);
    public byte[] renderPdf(URL url);
    public byte[] renderPdf(File file);
    public File renderToFile(File file, byte[] byteInput);
}
