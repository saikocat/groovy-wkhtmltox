package com.saikocat.wkhtmltox.inject;

import groovy.transform.CompileStatic;
import groovy.transform.CompileDynamic;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers

@CompileStatic
public class DefaultConfigModule extends AbstractModule {
    protected void configure() {
        bindListener(Matchers.any(), new Slf4jTypeListener());
    }
}
