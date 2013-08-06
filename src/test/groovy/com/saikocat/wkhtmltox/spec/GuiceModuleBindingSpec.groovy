package com.saikocat.wkhtmltox.spec;

import spock.lang.Specification;
import spock.guice.UseModules;

import com.google.inject.Inject;

import com.saikocat.wkhtmltox.inject.DefaultConfigModule;
import com.saikocat.wkhtmltox.helper.StreamHelper;
import com.saikocat.wkhtmltox.helper.impl.StreamHelperImpl;

@UseModules(DefaultConfigModule)
class GuiceModuleBindingSpec extends Specification {
    @Inject
    StreamHelper streamHelper;

    def "Default Guice binding"() {
        expect:
        streamHelper instanceof StreamHelperImpl
    }
}
