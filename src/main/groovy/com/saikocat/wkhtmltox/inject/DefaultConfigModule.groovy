package com.saikocat.wkhtmltox.inject;

import groovy.transform.CompileStatic;
import groovy.transform.CompileDynamic;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers

import com.saikocat.wkhtmltox.helper.*;
import com.saikocat.wkhtmltox.helper.impl.*;

@CompileStatic
public class DefaultConfigModule extends AbstractModule {
    protected void configure() {
        bind(StreamHelper).to(StreamHelperImpl);

        bindListener(Matchers.any(), new Slf4jTypeListener());
    }
}
