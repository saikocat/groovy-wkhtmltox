import com.google.inject.*;

import com.saikocat.wkhtmltox.inject.*;
import com.saikocat.wkhtmltox.helper.*;
import com.saikocat.wkhtmltox.renderer.*;

public class Test implements Runnable {
    @Inject
    PdfRenderer pdfRenderer;

    @Inject
    ImageRenderer imageRenderer;

    public void run() {
        def injector = Guice.createInjector(new DefaultConfigModule());
        injector.injectMembers(this);

        def tmpDir = System.getProperty("java.io.tmpdir");
        println ":: Temp Dir: ${tmpDir}"

        pdfRenderer.executable = "${tmpDir}/wkhtmltopdf";
        pdfRenderer.arguments << "--use-xserver"; // linux and without xvfb
        pdfRenderer.arguments.addAll([
            '--print-media-type',
            '-T', '20',
            '-B', '20'
        ]);

        renderToFile(
            new File("${tmpDir}/google.pdf"),
            pdfRenderer.render(new URL('http://google.com'))
        );
        renderToFile(
            new File("${tmpDir}/placekitten.pdf"),
            pdfRenderer.render(new File("../example/resources/placekitten.htm"))
        );


        imageRenderer.executable = "${tmpDir}/wkhtmltoimage";
        imageRenderer.arguments << "--use-xserver"; // linux and without xvfb
        renderToFile(
            new File("${tmpDir}/google.jpg"),
            imageRenderer.render(new URL('http://google.com'))
        );
    }

    public File renderToFile(File file, byte[] byteInput) {
        return file.withOutputStream {
            it.write(byteInput);
        }
    }
}
