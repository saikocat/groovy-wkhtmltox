import com.google.inject.*;

import com.saikocat.wkhtmltox.inject.*;
import com.saikocat.wkhtmltox.service.*;

public class Test implements Runnable {
    @Inject
    AsyncWkhtmltoxService asyncService;

    public void run() {
        def injector = Guice.createInjector(new DefaultConfigModule());
        injector.injectMembers(this);

        def tmpDir = System.getProperty("java.io.tmpdir");
        println ":: Temp Dir: ${tmpDir}"

        asyncService.setExecutables("${tmpDir}/wkhtmltopdf", "${tmpDir}/wkhtmltoimage");
        asyncService.arguments << "--use-xserver";

        String yahooToken = asyncService.renderPdf(new URL("http://yahoo.com.sg"));
        String googleToken = asyncService.renderImage(new URL("http://google.com"));

        while(!asyncService.isDone(yahooToken) || !asyncService.isDone(googleToken)) {
            Thread.sleep(500);
            println ":: Status: YH: ${asyncService.isDone(yahooToken) ? 'Done' : 'Rendering'} , GG: ${asyncService.isDone(googleToken)  ? 'Done' : 'Rendering' }"
        }

        asyncService.renderToFile(
            new File("${tmpDir}/google.jpg"),
            asyncService.output(googleToken)
        );
        asyncService.renderToFile(
            new File("${tmpDir}/yahoo.pdf"),
            asyncService.output(yahooToken)
        );

        asyncService.shutdown();
    }
}
