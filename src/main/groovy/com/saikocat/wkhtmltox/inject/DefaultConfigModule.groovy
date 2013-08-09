package com.saikocat.wkhtmltox.inject;

import groovy.transform.CompileStatic;
import groovy.transform.CompileDynamic;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers
import com.google.inject.name.Names;

import com.saikocat.wkhtmltox.helper.*;
import com.saikocat.wkhtmltox.helper.impl.*;
import com.saikocat.wkhtmltox.renderer.*;
import com.saikocat.wkhtmltox.renderer.impl.*;
import com.saikocat.wkhtmltox.service.*;
import com.saikocat.wkhtmltox.service.impl.*;

@CompileStatic
public class DefaultConfigModule extends AbstractModule {
    protected void configure() {
        bindConstants();
        bind(StreamHelper.class).to(StreamHelperImpl.class);
        bindRenderers();
        bindServices();
        bindListener(Matchers.any(), new Slf4jTypeListener());
    }

    protected void bindConstants() {
        bind(String.class)
            .annotatedWith(Names.named("DefaultCharset"))
            .toInstance("UTF-8");
    }

    protected void bindRenderers() {
        bind(PdfRenderer.class).to(PdfRendererImpl.class);
        bind(ImageRenderer.class).to(ImageRendererImpl.class);
    }

    protected void bindServices() {
        bind(WkhtmltoxService.class).to(WkhtmltoxServiceImpl.class);
    }
}
