package com.saikocat.wkhtmltox.renderer.impl;

import groovy.transform.CompileStatic;
import groovy.transform.CompileDynamic;

import com.google.inject.Inject;

import org.slf4j.Logger;

import com.saikocat.wkhtmltox.annotation.InjectLogger;
import com.saikocat.wkhtmltox.helper.StreamHelper;
import com.saikocat.wkhtmltox.renderer.ImageRenderer;

@CompileStatic
public class ImageRendererImpl extends BaseRendererImpl implements ImageRenderer {
    @InjectLogger
    Logger logger;

    @Inject
    public ImageRendererImpl(StreamHelper streamHelper) {
        this.streamHelper = streamHelper;
    }
}
