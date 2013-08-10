import com.google.inject.*;

import com.saikocat.wkhtmltox.inject.*;
import com.saikocat.wkhtmltox.service.*;

public class Test implements Runnable {
    @Inject
    WkhtmltoxService service;

    public void run() {
        def injector = Guice.createInjector(new DefaultConfigModule());
        injector.injectMembers(this);

        def tmpDir = System.getProperty("java.io.tmpdir");
        println ":: Temp Dir: ${tmpDir}"

        service.setExecutables("${tmpDir}/wkhtmltopdf", "${tmpDir}/wkhtmltoimage");
        service.arguments << "--use-xserver";

        service.renderToFile(
            new File("${tmpDir}/google.pdf"),
            service.renderPdf(new URL("http://google.com"))
        );

        service.renderToFile(
            new File("${tmpDir}/placekitten.jpg"),
            service.renderImage(new File("../example/resources/placekitten.htm"))
        );
    }
}
