package com.saikocat.wkhtmltox.spec;

import spock.lang.Specification;
import spock.guice.UseModules;

import com.google.inject.Inject;

import com.saikocat.wkhtmltox.inject.DefaultConfigModule;
import com.saikocat.wkhtmltox.helper.StreamHelper;
import com.saikocat.wkhtmltox.helper.impl.StreamHelperImpl;

@UseModules(DefaultConfigModule)
class StreamHelperSpec extends Specification {
    @Inject
    StreamHelper streamHelper;

    byte[] inputData;

    protected byte[] generateTestData(long size) {
        ByteArrayOutputStream baout = new ByteArrayOutputStream();
        for (int i = 0; i < size; i ++) {
            baout.write((byte) (i % 127) + 1);
        }
        return baout.toByteArray();
    }

    def setup() {
        inputData = generateTestData(1024 * 4);
    }

    def "copy from input stream into output stream"() {
        given: "input stream and byte array output stream"
        InputStream input = new ByteArrayInputStream(inputData);
        OutputStream output = new ByteArrayOutputStream();

        when: "call copy input stream into output stream"
        streamHelper.copy(input, output);

        then: "the content is copied into output stream"
        inputData == output.toByteArray();
    }

    def "convert an input stream into byte array"() {
        given: "an input stream"
        InputStream input = new ByteArrayInputStream(inputData);

        when: "I want to get the byte array of it"
        def byteArray = streamHelper.toByteArray(input)

        then: "a byte array is returned"
        inputData == byteArray
    }
}
