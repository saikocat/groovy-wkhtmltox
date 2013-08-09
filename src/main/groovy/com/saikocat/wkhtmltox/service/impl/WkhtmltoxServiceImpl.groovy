 package com.saikocat.wkhtmltox.service;

import groovy.transform.CompileStatic;
import groovy.transform.CompileDynamic;

import org.slf4j.Logger;

import com.google.inject.Inject;

import com.saikocat.wkhtmltox.annotation.InjectLogger;
import com.saikocat.wkhtmltox.exception.WkhtmltoxException;
import com.saikocat.wkhtmltox.renderer.ImageRenderer;
import com.saikocat.wkhtmltox.renderer.PdfRenderer;

@CompileStatic
public class WkhtmltoxServiceImpl implements WkhtmltoxService {
    @InjectLogger
    Logger logger;

    ImageRenderer imageRenderer;
    PdfRenderer pdfRenderer;

    // TODO: very simple abstraction for now
    List<String> arguments = [];

    @Inject
    public WkhtmltoxService(PdfRenderer pdfRenderer, ImageRenderer imageRenderer) {
        this.pdfRenderer = pdfRenderer;
        this.imageRenderer = imageRenderer;
    }

    public void setExecutables(String pdfExecutable, String imageExecutable) {
        this.pdfRenderer.executable = pdfExecutable;
        this.imageRenderer.executable = imageExecutable;
    }

    public byte[] renderImage(URL url) {
        imageRenderer.arguments = this.arguments;
        return imageRenderer.render(url);
    }

    public byte[] renderImage(File file) {
        imageRenderer.arguments = this.arguments;
        return imageRenderer.render(file);
    }

    public byte[] renderPdf(URL url) {
        pdfRenderer.arguments = this.arguments;
        return pdfRenderer.render(url);
    }

    public byte[] renderPdf(File file) {
        pdfRenderer.arguments = this.arguments;
        return pdfRenderer.render(file);
    }

    @CompileDynamic
    public File renderToFile(File file, byte[] byteInput) {
        return file.withOutputStream {
            it.write(byteInput);
        }
    }
}
