package com.saikocat.wkhtmltox.renderer.impl;

import groovy.transform.CompileStatic;
import groovy.transform.CompileDynamic;

import com.google.inject.Inject;

import org.slf4j.Logger;

import com.saikocat.wkhtmltox.annotation.InjectLogger;
import com.saikocat.wkhtmltox.exception.WkhtmltoxException;
import com.saikocat.wkhtmltox.renderer.BaseRenderer;
import com.saikocat.wkhtmltox.helper.StreamHelper;

@CompileStatic
public abstract class BaseRendererImpl implements BaseRenderer {
    @InjectLogger
    Logger logger;

    StreamHelper streamHelper;

    String executable; 
    List<String> arguments = [];

    public void checkExecutable() throws WkhtmltoxException {
        if(!(new File(executable)).exists()) {
            throw new WkhtmltoxException();
        }
    }

    public List<String> getCommands(String source) {
        List<String> commands = [ executable ];
        commands.addAll(arguments);
        commands << source;
        commands << "-"; // stdout
        return commands;
    }

    public String getCommandsToString(String source) {
        return getCommands(source).join(" ");
    }

    public byte[] render(final List<String> commands) {
        logger.debug(":: Commands: ${commands.join(' ')}");

        Runtime runtime = Runtime.getRuntime();
        ProcessBuilder builder = new ProcessBuilder(commands);
        final Process process = builder.start();
        byte[] data;

        try {
            data = streamHelper.toByteArray(process.inputStream);
            process.waitFor();
        } catch (Exception e) {
            throw new WkhtmltoxException(e);
        } finally {
            String error = streamHelper.getError(process.errorStream);
            process.closeStreams();
            process.destroy();
        }

        return data;
    }

    public byte[] render(URL url) {
        return render(getCommands(url.toString()));
    }

    public byte[] render(File file) {
        return render(getCommands(file.toString()));
    }
}
