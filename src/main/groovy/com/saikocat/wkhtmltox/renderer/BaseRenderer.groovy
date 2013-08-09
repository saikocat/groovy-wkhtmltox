package com.saikocat.wkhtmltox.renderer;

import groovy.transform.CompileStatic;
import groovy.transform.CompileDynamic;

@CompileStatic
public interface BaseRenderer {
    public void setExecutable(String executable);
    public String getExecutable();
    public void setArguments(List<String> arguments);
    public byte[] render(URL url);
    public byte[] render(File file);
}
