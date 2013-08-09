 package com.saikocat.wkhtmltox.service;

import groovy.transform.CompileStatic;
import groovy.transform.CompileDynamic;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Callable;

import org.slf4j.Logger;

import com.google.inject.Inject;

import com.saikocat.wkhtmltox.annotation.InjectLogger;
import com.saikocat.wkhtmltox.exception.WkhtmltoxException;

@CompileStatic
public class AsyncWkhtmltoxServiceImpl implements AsyncWkhtmltoxService {
    @InjectLogger
    Logger logger;

    WkhtmltoxService service;

    private static ExecutorService executor = Executors.newFixedThreadPool(4);
    private static ConcurrentHashMap<String, Future> tasks = new ConcurrentHashMap<String, Future>();

    // TODO: very simple abstraction for now
    List<String> arguments = [];

    @Inject
    public AsyncWkhtmltoxServiceImpl(WkhtmltoxService service) {
        this.service = service;
    }

    public void setExecutables(String pdfExecutable, String imageExecutable) {
        service.pdfRenderer.executable = pdfExecutable;
        service.imageRenderer.executable = imageExecutable;
    }

    public String queue(Closure closure) {
        String token = UUID.randomUUID().toString();
        Future future = executor.submit(closure as Callable);
        tasks[token] = future;
        return token;
    }

    public void shutdown() {
        executor.shutdown();
    }

    public byte[] output(String token) {
        Future future = tasks[token];
        return (future.isDone()) ? future.get() as byte[] : null;
    }

    public boolean isDone(String token) {
        return tasks[token].done;
    }

    public String renderImage(URL url) {
        return queue {
            service.imageRenderer.arguments = this.arguments;
            return service.imageRenderer.render(url);
        }
    }

    public String renderImage(File file) {
        return queue {
            service.imageRenderer.arguments = this.arguments;
            return service.imageRenderer.render(file);
        }
    }

    public String renderPdf(URL url) {
        return queue {
            service.pdfRenderer.arguments = this.arguments;
            return service.pdfRenderer.render(url);
        }
    }

    public String renderPdf(File file) {
        return queue {
            service.pdfRenderer.arguments = this.arguments;
            return service.pdfRenderer.render(file);
        }
    }

    public File renderToFile(File file, byte[] byteInput) {
        return service.renderToFile(file, byteInput);
    }
}
